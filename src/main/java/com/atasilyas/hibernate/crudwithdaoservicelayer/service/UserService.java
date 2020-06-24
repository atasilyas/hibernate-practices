package com.atasilyas.hibernate.crudwithdaoservicelayer.service;

import com.atasilyas.hibernate.entity.User;

public interface UserService
{
    void save(User user);
    void update(User user);
    User findById(Integer id);
    void delete(User user);
}
