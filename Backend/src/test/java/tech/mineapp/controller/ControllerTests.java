package tech.mineapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {

    @Autowired
    private AuthController authController;

    @Autowired
    private ForgotPasswordController forgotPasswordController;

    @Autowired
    private UserController userController;

    @Autowired
    private VerificationController verificationController;

    @Test
    public void AuthControllerLoads() {
        assertThat(authController).isNotNull();
    }

    @Test
    public void ForgotPasswordControllerLoads() {
        assertThat(forgotPasswordController).isNotNull();
    }

    @Test
    public void UserControllerLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void VerificationControllerLoads() {
        assertThat(verificationController).isNotNull();
    }
}
