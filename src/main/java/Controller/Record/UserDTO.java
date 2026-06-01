package Controller.Record;

import org.ONE.model.entity.ENUM.Permission;

public record UserDTO(String userName, Permission permission, String email) {
}
