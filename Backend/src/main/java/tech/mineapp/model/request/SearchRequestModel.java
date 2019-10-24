package tech.mineapp.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class SearchRequestModel {
	@NotBlank
	private String query;
}
