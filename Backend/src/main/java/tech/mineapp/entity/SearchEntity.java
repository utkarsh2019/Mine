package tech.mineapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tech.mineapp.constants.Category;

/**
 * Entity for storing user's searches
 *
 * @author utkarsh, amolmoses
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Searches")
public class SearchEntity implements Serializable {

	@Id
	@Column(nullable = false, unique = true)
	private Long searchId;
	
	@Column(nullable = false)
	private String query;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Category category;

	@NotNull
	@Column(nullable = false)
	private Timestamp lastModified;

	@NotNull
	@Column(nullable = false)
	private int numOfSearches;
	
	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, referencedColumnName = "userId")
    private UserEntity user;
}
