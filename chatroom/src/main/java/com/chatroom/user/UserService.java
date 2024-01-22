package com.chatroom.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void saveUser(User user){
        log.info("User Details : "+user);
        user.setStatus(Status.ONLINE);
        user  = userRepository.save(user);
        log.info("After : "+user);
    }

    public void disconnect(User user){
        var storedUser = userRepository.findById(user.getNickName()).orElse(null);
        if(storedUser!=null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<?> findConnectedUsers(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
