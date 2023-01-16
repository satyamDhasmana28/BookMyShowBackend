package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.TheaterConverter;
import com.satyam.BookMyShowBackend.Enum.SeatType;
import com.satyam.BookMyShowBackend.Model.Theatre;
import com.satyam.BookMyShowBackend.Model.TheatreSeat;
import com.satyam.BookMyShowBackend.Repository.TheaterRepository;
import com.satyam.BookMyShowBackend.RequestDto.TheatreRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheaterRepository theaterRepository;
    public String addTheatreInDb(TheatreRequestDto theatreRequestDto) {
        Theatre theatre = TheaterConverter.convertDtoToEntity(theatreRequestDto);
        List<TheatreSeat> theatreSeatList = createTheatreSeat(5,5);
//        setting theatreSeat property of theatre
        theatre.setTheatreSeatList(theatreSeatList);
//        setting Theatre property of TheatreSeat
        for(TheatreSeat theatreSeat:theatreSeatList){
            theatreSeat.setTheatre(theatre);
        }
        theaterRepository.save(theatre);
        return "theatre added successfully";
    }

//    to create theatreSeats inside Theatre
    public List<TheatreSeat> createTheatreSeat(int classic,int platinum){
        List<TheatreSeat> theatreSeatList = new ArrayList<>();
        for(int i=1;i<=classic;i++){
            String seatno = "1"+(char)(64+i);
            TheatreSeat theatreSeat= new TheatreSeat(seatno,100, SeatType.CLASSIC);
            theatreSeatList.add(theatreSeat);
        }
        for(int i=1;i<=platinum;i++){
            String seatno = "2"+(char)(64+i);
            TheatreSeat theatreSeat= new TheatreSeat(seatno,200, SeatType.PLATINUM);
            theatreSeatList.add(theatreSeat);
        }
        return  theatreSeatList;
    }
}
