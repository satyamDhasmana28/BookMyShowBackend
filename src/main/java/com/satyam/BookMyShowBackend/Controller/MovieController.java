package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.RequestDto.MovieRequestDto;
import com.satyam.BookMyShowBackend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
   @PostMapping("/add")
   public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto){
       String response = movieService.addMovieInDb(movieRequestDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
   }
}
