package com.ourchat.system.signup.service;

import com.ourchat.system.signup.entiy.CustomerDTO;

public interface SignUpService {
    Boolean create(CustomerDTO customerDTO,String iconPath);
}
