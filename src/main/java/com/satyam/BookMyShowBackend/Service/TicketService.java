package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.TheaterConverter;
import com.satyam.BookMyShowBackend.Converter.TicketConverter;
import com.satyam.BookMyShowBackend.Model.Show;
import com.satyam.BookMyShowBackend.Model.ShowSeat;
import com.satyam.BookMyShowBackend.Model.Ticket;
import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.*;
import com.satyam.BookMyShowBackend.RequestDto.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public Double bookTickets(TicketRequestDto ticketRequestDto) throws Exception {
        int showId=ticketRequestDto.getShowId();
        int userId= ticketRequestDto.getUserId();
        System.out.println(showId);
        System.out.println(userId);
        Show show = showRepository.findById(showId).get();
        User user = userRepository.findById(userId).get();
//        Show show = showRepository.findById(ticketRequestDto.getShowId()).get();
//        List<ShowSeat> showSeatList =  show.getShowSeatList();
        List<String> seatList = ticketRequestDto.getSeatList();
        if(isSeatBooked(seatList,showId)){
//            unbooked those seat which are accidentally booked by isBooked methor
            throw new Exception("seat is booked");
        }
        List<ShowSeat> allotedSeats = new ArrayList<>();
        double price = calculateBookingAmountAndFillShowSeatList(seatList,allotedSeats,showId);
//        ticketConverter is not working doing explicitely
//        Ticket ticket = TicketConverter.convertDtoToEntity(ticketRequestDto);
//        create List<ShowSeat> seatList to string; and set seat in ticket
//        Setting the atrribute of ticket
        Ticket ticket=new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setAmount((int) price);
        ticket.setBookedSeats(allotedSeats);
        String allotedseatString = getAllocatedSeatsInString(seatList);
        ticket.setAllottedSeats(allotedseatString);
//setting the tikcetList Atrribute of User and Show
        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);
//        userRepository.findById(ticketRequestDto.getUserId()).get().getTicketList().add(ticket);
//        showRepository.findById(ticketRequestDto.getShowId()).get().getTicketList().add(ticket);
//        saving the ticket
        userRepository.save(user);
        showRepository.save(show);
//        ticketRepository.save(ticket);
        return price;
    }
    private boolean isSeatBooked(List<String> seatList,int showId){
        for(String seatNo : seatList){
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo,showId);
            if(showSeat.isBooked()){
                return true;
            }
            showSeat.setBooked(true);
        }
        return false;
    }
    private double calculateBookingAmountAndFillShowSeatList(List<String> seatList,List<ShowSeat> bookedShowSeatList,int showId){
        double price =0.0;
        for(String seatNo : seatList){
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo,showId);
            bookedShowSeatList.add(showSeat);
            price+=showSeat.getRate();
        }
        return price;
    }
    public String getAllocatedSeatsInString(List<String> seatStringList){
        String str="";
        for(String seatNo : seatStringList){
            str+=(seatNo+",");
        }
        return str;
    }
}
