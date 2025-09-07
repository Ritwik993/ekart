package com.ecom.ekart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
//        return ResponseEntity.ok(userService.fetchAllUsers());
    }

//    @GetMapping("/api/users/{id}")
//    public ResponseEntity<User> getAUser(@PathVariable Long id){
//        User user=userService.fetchAUser(id);
//        if(user==null)
//            return ResponseEntity.notFound().build();
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getAUser(@PathVariable Long id){
       return userService.fetchAUser(id)
               .map(ResponseEntity::ok)
               .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user){ //we need to @RequestBody annotation to specify how we want to recieve the response from the user
        userService.addUser(user);
        return "User added successfully";
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
        boolean updated= userService.updateUser(id,updatedUser);
        if(updated)
            return new ResponseEntity<>("Successfully updated !",HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }
}
