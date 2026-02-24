package com.donaMaria_.demo.services;

import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.models.User;
import com.donaMaria_.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User findByEmail(String email) throws Exception {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new RecursoNaoEncontradoException("email");
        }
        return user;
    }

}
