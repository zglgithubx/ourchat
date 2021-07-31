package com.ourchat.system.signup.service.impl;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.login.mapper.CustomerMapper;
import com.ourchat.system.signup.entiy.CustomerDTO;
import com.ourchat.system.signup.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Boolean create(CustomerDTO customerDTO,String iconPath) {
        Customer customer=new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setName(customerDTO.getName());
        customer.setAge(customerDTO.getAge());
        if("保密".equals(customerDTO.getSex())){
            customer.setGender(null);
        }else{
            customer.setGender(!"女".equals(customerDTO.getSex()));
        }
        customer.setAddress(customerDTO.getRegion());
        customer.setIconPath(iconPath);
        System.out.println(customer.toString());
        return customerMapper.addCustomer(customer);

    }
}
