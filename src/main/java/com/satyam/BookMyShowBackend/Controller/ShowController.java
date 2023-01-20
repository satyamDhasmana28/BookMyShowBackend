package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.Model.Show;
import com.satyam.BookMyShowBackend.RequestDto.ShowRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.ShowTimeRequestDto;
import com.satyam.BookMyShowBackend.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowRequestDto showRequestDto){
        String response = showService.addShowInDb(showRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/get-all-shows")
    public ResponseEntity<List<Show>> getAllShowFromXTOY(@RequestBody ShowTimeRequestDto showTimeRequestDto){
        List<Show> showList = showService.getAllShowFromXToY(showTimeRequestDto);
        return new ResponseEntity<>(showList,HttpStatus.FOUND);
    }
}
