/**
 * 
 */
package tech.mineapp.entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@Entity
@Table(name = "VerificationTokens")
public class VerificationTokenEntity {
	private static final int EXPIRATION = 60 * 24;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private String token;
   
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity user;
     
    private Date expiryDate;
    
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
