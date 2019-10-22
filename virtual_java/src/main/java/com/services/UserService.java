package com.services;

import com.common.ServiceResponse;

public interface UserService {
    ServiceResponse login(String username, String password);
}
