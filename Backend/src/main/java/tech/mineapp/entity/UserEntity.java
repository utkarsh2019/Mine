package tech.mineapp.entity;

import lombok.Data;
import tech.mineapp.constants.AuthProvider;
import tech.mineapp.constants.Category;

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

    @Enumerated(EnumType.STRING)
    private Category preference1;
    
    @Enumerated(EnumType.STRING)
    private Category preference2;
    
    @Enumerated(EnumType.STRING)
    private Category preference3;
    
    @Enumerated(EnumType.STRING)
    private Category preference4;
    
    @Enumerated(EnumType.STRING)
    private Category preference5;
    
    @Enumerated(EnumType.STRING)
    private Category preference6;
    
    private String providerId;
}
