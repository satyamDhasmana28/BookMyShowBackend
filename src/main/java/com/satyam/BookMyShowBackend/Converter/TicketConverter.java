package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.*;
//import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.ShowRepository;
import com.satyam.BookMyShowBackend.Repository.UserRepository;
import com.satyam.BookMyShowBackend.RequestDto.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TicketConverter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    public static Ticket convertDtoToEntity(TicketRequestDto ticketRequestDto) {
        TicketConverter ticketConverter = new TicketConverter();
        User user = ticketConverter.userRepository.findById(ticketRequestDto.getUserId()).get();
        Show show = ticketConverter.showRepository.findById(ticketRequestDto.getShowId()).get();
        Ticket ticket = Ticket.builder().show(show).user(user).bookedAt(new Date()).build();
        return ticket;
    }
}
