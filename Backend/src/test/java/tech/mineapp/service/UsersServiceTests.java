package tech.mineapp.service;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.userdetails.UserDetails;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.UserRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class UsersServiceTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void testLoadUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("name");
        userEntity.setPassword("password");
        userEntity.setEmail("email");

        when(userRepository.findUserByEmail("email")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userService.loadUserByUsername("email");

        assertThat(
                "Email should be correctly set",
                userDetails.getUsername(),
                equalTo("email")
        );

        assertThat(
                "Password should be correctly set",
                userDetails.getPassword(),
                equalTo("password")
        );
    }

    @Test
    public void testLoadUserById() {
        String userId = "123456789";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(Long.parseLong(userId));
        userEntity.setPassword("password");
        userEntity.setEmail("email");

        when(userRepository.findUserByUserId(Long.parseLong(userId))).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userService.loadUserById(userId);

        assertThat(
                "Email should be correctly set",
                userDetails.getUsername(),
                equalTo("email")
        );

        assertThat(
                "Password should be correctly set",
                userDetails.getPassword(),
                equalTo("password")
        );
    }

    @Test
    public void checkGenerateIdForUserGeneratesRandomIds() {
        Long firstId = userService.generateIdForUser();
        Long secondId = userService.generateIdForUser();

        assertThat(
                "Both the Ids should be different",
                firstId,
                not(equalTo(secondId))
        );
    }
}
