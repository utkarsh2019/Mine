package tech.mineapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static tech.mineapp.constants.Constants.ApplicationConstants.userIdLength;

/**
 * Entity representing persistent storage
 * of user information
 *
 * @author amolmoses
 */
@Data
@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {

    @Id
    @Column(nullable = false, unique = true, length = userIdLength)
    private String userId;

    @Column(nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Column(nullable = false)
    private Boolean isVerified = false;
}
