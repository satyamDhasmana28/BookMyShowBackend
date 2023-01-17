package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.UserRepository;
import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverter {
    @Autowired
    UserRepository userRepository;
    public static User convertDtoToEntity(UserRequestDto userRequestDto) {
        User user = User.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        return user;
    }

}
