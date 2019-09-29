package tech.mineapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Column(nullable = false)
    private Boolean isVerified = false;
}
