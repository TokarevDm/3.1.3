package com.example.bootstrap_3_1_3.service;

import com.example.bootstrap_3_1_3.dao.RoleRepository;
import com.example.bootstrap_3_1_3.dao.UserRepository;
import com.example.bootstrap_3_1_3.models.Role;
import com.example.bootstrap_3_1_3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public User findById(Long userid) {
        Optional<User> userFromDB = userRepository.findById(userid);
        return userFromDB.orElse(new User());
    }


    public boolean save(User user) {
        User userBD = userRepository.findByEmail(user.getUsername());
        if (userBD != null) {
            return false;
        }
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        userRepository.save(user);
        return true;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public List<Role> allRoles() {
        return roleRepository.findAll();

    }

    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}







