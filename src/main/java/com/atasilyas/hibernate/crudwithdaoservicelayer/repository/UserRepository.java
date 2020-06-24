package com.atasilyas.hibernate.crudwithdaoservicelayer.repository;

import com.atasilyas.hibernate.entity.User;

public interface UserRepository
{
    void save(User user);
    void update(User user);
    User findById(Integer id);
    void delete(User user);
}
