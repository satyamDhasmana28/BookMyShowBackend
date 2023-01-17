package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.UserUpdateRequestDto;
import com.satyam.BookMyShowBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto){
        String response = userService.addUserInDb(userRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id")int id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto){
        String response = userService.updateUser(userUpdateRequestDto);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam("id") int id){
        String response = userService.deleteUserById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
