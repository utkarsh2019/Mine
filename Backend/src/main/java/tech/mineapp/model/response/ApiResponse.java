package tech.mineapp.model.response;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class ApiResponse {
	private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
