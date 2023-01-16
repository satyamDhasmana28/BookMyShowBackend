package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;

public class UserConverter {
    public static User convertDtoToEntity(UserRequestDto userRequestDto) {
        User user = User.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        return user;
    }
}
