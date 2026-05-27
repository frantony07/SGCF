package Controller.Record;

import org.ONE.model.entity.User;

import java.time.LocalDateTime;

public record PasswordResetDTO(LocalDateTime expiration, String token, User user) {
}
