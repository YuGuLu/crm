package com.yu.crm.settings.service;

import com.yu.crm.exception.LoginException;
import com.yu.crm.settings.domain.User;

import java.util.List;

/**
 *
 */
public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
