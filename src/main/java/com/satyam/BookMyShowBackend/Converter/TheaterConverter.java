package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.Theatre;
import com.satyam.BookMyShowBackend.RequestDto.TheatreRequestDto;

public class TheaterConverter {
//    adding theater 5 classic and 5 platinum by deafult
    public static Theatre convertDtoToEntity(TheatreRequestDto theatreRequestDto) {
        Theatre theatre = Theatre.builder().city(theatreRequestDto.getCity()).name(theatreRequestDto.getName()).
                          address(theatreRequestDto.getAddress()).build();
        return theatre;
    }
}
