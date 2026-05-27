package org.ONE.model.services;

import org.ONE.model.entity.PasswordReset;
import org.ONE.model.entity.User;

public interface PasswordRecordService {
    void requestPasswordReset(User user);
    PasswordReset validateToken(String code) throws Exception;

}
