package tech.mineapp.security;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.mineapp.model.response.ContainerResponseModel;

/**
 * @author utkarsh
 *
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ContainerResponseModel response = new ContainerResponseModel();
        response.setVerb(httpServletRequest.getMethod());
        response.setEndpoint(httpServletRequest.getRequestURI());
        response.setStatus("FAIL");
        response.setErrorMessage("Unauthorized user");
        mapper.writeValue(new File("c:\\response.json"), response);
        String jsonInString = mapper.writeValueAsString(response);
        httpServletResponse.getWriter().write(jsonInString);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}