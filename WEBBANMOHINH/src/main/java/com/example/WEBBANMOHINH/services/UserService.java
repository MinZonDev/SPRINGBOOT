package com.example.WEBBANMOHINH.services;

import jakarta.transaction.Transactional;
import com.example.WEBBANMOHINH.entity.Role;
import com.example.WEBBANMOHINH.entity.User;
import com.example.WEBBANMOHINH.repository.RoleRepository;
import com.example.WEBBANMOHINH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public void add(User newUser) {
        userRepository.save(newUser);
    }
    @Transactional
    public void addUserWithRole(User user) {
        User savedUser = userRepository.save(user);

        // Tìm role có ID là 1
        Role role = roleRepository.findById(3).orElseThrow(() -> new RuntimeException("Role not found"));

        savedUser.getRoles().add(role);
        userRepository.save(savedUser);
    }
}
