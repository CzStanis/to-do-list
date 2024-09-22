//package com.Wproject.to_do_list.service;
//
//import com.Wproject.to_do_list.entity.User;
//import com.Wproject.to_do_list.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private UserRepository userRepository;
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    public void registerNewUser(User user) {
//        Optional<String> userOptional = userRepository.
//                findByEmail(user.getEmail());
//        if(userOptional.isPresent()) {
//            throw new IllegalStateException("email taken");
//        }
//        userRepository.save(user);
//    }
//}
