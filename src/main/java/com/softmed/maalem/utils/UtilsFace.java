package com.softmed.maalem.utils;

import com.softmed.maalem.persistence.entity.User;

public interface UtilsFace {
    Boolean sendActivationMail(User user);
}
