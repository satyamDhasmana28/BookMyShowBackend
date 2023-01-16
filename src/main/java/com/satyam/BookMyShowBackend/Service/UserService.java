package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.UserConverter;
import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.UserRepository;
import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addUserInDb(UserRequestDto userRequestDto) {
        User user = UserConverter.convertDtoToEntity(userRequestDto);
        userRepository.save(user);
        return "Hi "+user.getName()+" :)";
    }
}
