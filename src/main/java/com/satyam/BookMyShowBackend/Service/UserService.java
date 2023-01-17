package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.UserConverter;
import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.UserRepository;
import com.satyam.BookMyShowBackend.RequestDto.UserRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.UserUpdateRequestDto;
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

    public User getUserById(int id) {
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        return null;

    }

    public String deleteUserById(int id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return "successfully deleted :( ";
        }
        return "no user exists with id "+id;

    }

    public String updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        int id = userUpdateRequestDto.getId();
        if(userRepository.findById(id).isPresent()){
//            updating user
            User user = userRepository.findById(id).get();
            user.setName(userUpdateRequestDto.getName());
            user.setMobile(userUpdateRequestDto.getMobile());
            userRepository.save(user);
            return "successfully updated :)";
        }
        return "user not exists :(";
    }
}
