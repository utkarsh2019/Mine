/**
 * 
 */
package tech.mineapp.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author utkarsh
 *
 */
@Data
@Entity
@Table(name = "VerificationTokens")
public class VerificationTokenEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(nullable = false)
    private String token;
   
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;
    
    @Column(nullable = false)
    private Date expiryDate;
}
