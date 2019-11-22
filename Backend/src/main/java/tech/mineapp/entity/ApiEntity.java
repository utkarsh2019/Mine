package tech.mineapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Apis")
public class ApiEntity implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
       
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity user;
    
    @Column(nullable = false)
    private Boolean youtube = true;
    
    @Column(nullable = false)
    private Boolean vimeo = true;
    
    @Column(nullable = false)
    private Boolean dailymotion = true;
    
    @Column(nullable = false)
    private Boolean tvmaze = true;
    
    @Column(nullable = false)
    private Boolean tmdb = true;
    
    @Column(nullable = false)
    private Boolean googlebooks = true;
    
    @Column(nullable = false)
    private Boolean newsapi = true;
    
    @Column(nullable = false)
    private Boolean seatgeek = true;
}
