package com.donaMaria_.demo.services;

import com.donaMaria_.demo.Dtos.ReqUserDto;
import com.donaMaria_.demo.Dtos.ResUserDto;
import com.donaMaria_.demo.exceptions.EmailJaCadastradoException;
import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.exceptions.RoleInvalidaException;
import com.donaMaria_.demo.models.Role;
import com.donaMaria_.demo.models.User;
import com.donaMaria_.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User findByEmail(String email){
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new RecursoNaoEncontradoException("email");
        }
        return user;
    }

    public User createUser(ReqUserDto userDto){
        if (repository.existsByEmail(userDto.email())) {
            throw new EmailJaCadastradoException();
        }

        User u = new User();

        try {
            u.setRole(Role.valueOf(userDto.role()));
        } catch (IllegalArgumentException e) {
            throw new RoleInvalidaException();
        }

        u.setName(userDto.name());
        u.setEmail(userDto.email());
        u.setPassword(encoder.encode(userDto.password()));
        u.setCreate_date(Instant.now());

        repository.save(u);
        return u;
    }

    public void deleteUser(long id){
        if (!repository.existsById(id)){
            throw new RecursoNaoEncontradoException("user");
        }

        repository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário"));
    }




}
