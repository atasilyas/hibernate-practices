package com.atasilyas.hibernate.crudwithdaoservicelayer.service;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.crudwithdaoservicelayer.repository.UserRepository;

public class UserServiceImpl implements  UserService
{

    private  final  UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public void save(User user)
    {
        userRepository.save(user);
    }

    public void update(User user)
    {
        userRepository.update(user);

    }

    public User findById(Integer id)
    {
        return userRepository.findById(id);
    }

    public void delete(User user)
    {
        userRepository.delete(user);
    }
}
