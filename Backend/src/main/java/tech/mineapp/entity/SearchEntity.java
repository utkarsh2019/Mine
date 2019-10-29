package tech.mineapp.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tech.mineapp.constants.Category;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Searches")
public class SearchEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String query;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity user;
}
