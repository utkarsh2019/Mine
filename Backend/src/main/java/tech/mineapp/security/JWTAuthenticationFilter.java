package tech.mineapp.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.mineapp.util.SpringApplicationContext;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.LoginResponseModel;
import tech.mineapp.model.service.LoginDTO;
import tech.mineapp.repository.UserRepository;

import static tech.mineapp.constants.Constants.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDTO loginDTO = new ObjectMapper().readValue(req.getInputStream(), LoginDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmailId(),
                            loginDTO.getPassword(),
                            Collections.emptyList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        String emailId = ((User) auth.getPrincipal()).getUsername();

        String token = JWT.create()
                .withSubject(emailId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        
        String responseBody = retrieveResponseBody(emailId);
        writeResponseBody(res, responseBody);
    }

    private String retrieveResponseBody(String emailId) throws JsonProcessingException {
		
		ContainerResponseModel responseObj = new ContainerResponseModel();
        responseObj.setVerb("POST");
        responseObj.setEndpoint("/api/login");
        responseObj.setStatus("SUCCESS");
        
        LoginResponseModel loginResponse = new LoginResponseModel();
        loginResponse.setUserId(retrieveUserId(emailId));
        responseObj.setResponseObject(loginResponse);
        
        return new ObjectMapper().writeValueAsString(responseObj);
	}

    private String retrieveUserId(String emailId) {
         UserRepository userRepository = (UserRepository) SpringApplicationContext.getBean(UserRepository.class);
         return userRepository.findUserByEmailId(emailId).getUserId();
    }

    private void writeResponseBody(HttpServletResponse res, String responseBody) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(responseBody);
        out.flush();
    }
}