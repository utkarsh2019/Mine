package tech.mineapp.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;
}
