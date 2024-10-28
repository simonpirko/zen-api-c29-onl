package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.entity.Role;
import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Role role = new Role();
        role.setUser(user);
        role.setTypeOfRole("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setAuthorities(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userService.save(user);
        return ResponseEntity.ok(save);
    }
}
