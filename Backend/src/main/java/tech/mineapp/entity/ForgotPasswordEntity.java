package tech.mineapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "ForgotPasswordTokens")
public class ForgotPasswordEntity implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(nullable = false)
    private String token;
   
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity user;
    
    @Column(nullable = false)
    private Date expiryDate;
}
