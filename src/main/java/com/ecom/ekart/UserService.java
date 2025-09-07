package com.ecom.ekart;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private Long nextId=1L;
    public List<User> userList=new ArrayList<>();

    public List<User> fetchAllUsers(){
        return userList;
    }

//    public User fetchAUser(Long id){
//        for(User user:userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;
//    }


    public Optional<User> fetchAUser(Long id){
        return userList.stream()
                .filter(user->user.getId().equals(id))
                .findFirst();
    }

    public List<User> addUser(User user){
        user.setId(nextId++); // we are overwriting  id generated from the service layer
        userList.add(user);
        return userList;
    }

    public boolean updateUser(Long id,User updatedUser){
        return userList.stream()
                .filter(user->user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                })
                .orElse(false);
    }
}
