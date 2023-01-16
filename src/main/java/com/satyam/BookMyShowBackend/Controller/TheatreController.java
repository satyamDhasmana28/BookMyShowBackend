package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.Model.Theatre;
import com.satyam.BookMyShowBackend.Model.TheatreSeat;
import com.satyam.BookMyShowBackend.RequestDto.TheatreRequestDto;
import com.satyam.BookMyShowBackend.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
    @Autowired
    TheatreService theatreService;
    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        String response = theatreService.addTheatreInDb(theatreRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
