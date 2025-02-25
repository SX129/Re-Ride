package com.re_ride.userms.user.impl;

import com.re_ride.userms.user.User;
import com.re_ride.userms.user.UserRepository;
import com.re_ride.userms.user.UserService;
import com.re_ride.userms.user.messaging.RabbitMQConfig;
import com.re_ride.userms.user.messaging.UserEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;

    public UserServiceImpl(UserRepository userRepository, RabbitTemplate rabbitTemplate){
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllRiders() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUserType() == User.UserType.RIDER)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);

        UserEvent.UserType userType = UserEvent.UserType.valueOf(user.getUserType().name());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                new UserEvent(user.getUserId(), userType, user.getFirstName(), user.getEmail())
        );

        return user;
    }

    @Override
    public User updateUserById(Long userId, User user) {
        if(getUserById(userId) != null){
            User updatedUser = getUserById(userId);

            if(user.getEmail() != null){
                updatedUser.setEmail(user.getEmail());
            }

            if(user.getPhoneNumber() != null){
                updatedUser.setPhoneNumber(user.getPhoneNumber());
            }

            if(user.getFirstName() != null){
                updatedUser.setFirstName(user.getFirstName());
            }

            if(user.getLastName() != null){
                updatedUser.setLastName(user.getLastName());
            }

            if(user.getPassword() != null){
                updatedUser.setPassword(user.getPassword());
            }

            userRepository.save(updatedUser);
            return updatedUser;
        }

        return null;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        if(getUserById(userId) != null){
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}
