package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.ShowConverter;
import com.satyam.BookMyShowBackend.Enum.SeatType;
import com.satyam.BookMyShowBackend.Model.*;
import com.satyam.BookMyShowBackend.Repository.MovieRepository;
import com.satyam.BookMyShowBackend.Repository.ShowRepository;
import com.satyam.BookMyShowBackend.Repository.TheaterRepository;
import com.satyam.BookMyShowBackend.RequestDto.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    public String addShowInDb(ShowRequestDto showRequestDto) {
        int movieId = showRequestDto.getMovieId();
        int theatreId = showRequestDto.getTheatreId();
//        if movieId or theatreID does not exist
        if(movieRepository.findById(movieId).isEmpty() ||
                theaterRepository.findById(theatreId).isEmpty() ){
            return "movieId or theatreId doesn't exist";
        }
        Show show = ShowConverter.convertDtoToEntity(showRequestDto);
        Movie movie = movieRepository.findById(movieId).get();
        Theatre theatre = theaterRepository.findById(theatreId).get();

//        setting movie and theatre attribute of show;
        show.setTheatre(theatre);
        show.setMovie(movie);
//        here I have to set showSeatList in show and passing the multiplier;
        List<ShowSeat> showSeatList = createShowSeat(theatre.getTheatreSeatList(),showRequestDto.getMultiplier());
        show.setShowSeatList(showSeatList);
//        set the Show attribute in every showSeat in showSeatList
        for(ShowSeat showSeat : showSeatList){
            showSeat.setShow(show);
        }
//        here I will fetch List<show> from movie then add the currentShow in the List<show> and update the List<Show> attribute of movie
        List<Show> showList = movie.getListOfShow();
        showList.add(show);
        movie.setListOfShow(showList);
//        here I will fetch List<show> from theatre then add the currentShow in the List<show> and update the List<Show> attribute of theatre
        List<Show> showListInTheatre = theatre.getShowList();
        showListInTheatre.add(show);
        theatre.setShowList(showListInTheatre);
//      saving show
        showRepository.save(show);

        return "show successfully added :)";
    }
//    function to get List<ShowSeat> by passing List<TheatreSeat> and RateMultiplier
    private List<ShowSeat> createShowSeat(List<TheatreSeat> theatreSeatList,double multiplier){
        List<ShowSeat> showSeatList = new ArrayList<>();
        for(TheatreSeat theatreSeat : theatreSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeat.getSeatNo());
            showSeat.setSeatType(theatreSeat.getSeatType());
            showSeat.setRate(multiplier*theatreSeat.getRate());
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }
}
