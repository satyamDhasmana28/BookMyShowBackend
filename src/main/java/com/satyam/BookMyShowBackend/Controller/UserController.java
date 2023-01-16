package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;
import com.satyam.BookMyShowBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
