package tech.mineapp.entity;

import org.junit.Test;
import tech.mineapp.constants.AuthProvider;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserEntityTest {

    @Test
    public void testUserEntity(){
        Long userId = Long.valueOf(123456789);
        String name = "name";
        String password = "password";
        String email = "email";
        String profilePic = "testProfilePic";
        String providerId = "testProviderId";


        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setProfilePicUrl(profilePic);
        userEntity.setProviderId(providerId);
        userEntity.setProvider(AuthProvider.local);

        assertThat(
                "UserId should be correctly set",
                userEntity.getUserId(),
                equalTo(userId)
        );

        assertThat(
                "Name should be correctly set",
                userEntity.getName(),
                equalTo(name)
        );

        assertThat(
                "Email should be correctly set",
                userEntity.getEmail(),
                equalTo(email)
        );

        assertThat(
                "Password should be correctly set",
                userEntity.getPassword(),
                equalTo(password)
        );

        assertThat(
                "Profile picture should be correctly set",
                userEntity.getProfilePicUrl(),
                equalTo(profilePic)
        );

        assertThat(
                "ProviderId should be correctly set",
                userEntity.getProviderId(),
                equalTo(providerId)
        );

        assertThat(
                "Provider should be correctly set",
                userEntity.getProvider(),
                equalTo(AuthProvider.local)
        );
    }
}
