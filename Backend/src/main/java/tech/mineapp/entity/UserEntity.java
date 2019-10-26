package tech.mineapp.entity;

import lombok.Data;
import tech.mineapp.constants.AuthProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Entity representing persistent storage
 * of user information
 *
 * @author amolmoses, utkarsh
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private Long userId;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column
    private String password;

    private String profilePicUrl;

    @Column(nullable = false)
    private Boolean isVerified = false;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    
    @NotNull
    private int noOfSearches = 3;

    @NotNull
    private String categoryPreferences; // TODO: Re-think storing of user preferences
    
    private String providerId;
}
