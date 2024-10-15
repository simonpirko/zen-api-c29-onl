package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudOperation<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            throw new IllegalArgumentException("Username already exists");
//        }
//
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("Email already exists");
//        }
//
//        return userRepository.save(user);

        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
//        User existingUser = userRepository.findById(user.getId())
//                .orElseThrow(() -> new NotFoundException("User not Found"));
//
//        existingUser.setUsername(user.getUsername());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setPhone(user.getPhone());
//
//        return userRepository.save(existingUser);

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found"));
    }
}