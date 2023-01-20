package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.TheaterConverter;
import com.satyam.BookMyShowBackend.Converter.TicketConverter;
import com.satyam.BookMyShowBackend.Model.Show;
import com.satyam.BookMyShowBackend.Model.ShowSeat;
import com.satyam.BookMyShowBackend.Model.Ticket;
import com.satyam.BookMyShowBackend.Model.User;
import com.satyam.BookMyShowBackend.Repository.*;
import com.satyam.BookMyShowBackend.RequestDto.TicketCancelRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.TicketRequestDto;
import com.satyam.BookMyShowBackend.ResponseDto.TicketCancelResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        int showId = ticketRequestDto.getShowId();
        int userId = ticketRequestDto.getUserId();
//        checking if show or user exists
        if (showRepository.findById(showId).isEmpty() || userRepository.findById(userId).isEmpty()) {
            throw new Exception("show or movie does not exists");
        }
//        fetching show and user object
        Show show = showRepository.findById(showId).get();
        User user = userRepository.findById(userId).get();
//        getting ticket user wants to book from ticketRequestDto
        List<String> seatListFromTicketRequestDto = ticketRequestDto.getSeatListFromTicketRequestDto();
//        if seatListFromTicketRequestDto has any seat which uis already booked then throw exception
        if (isSeatBooked(seatListFromTicketRequestDto, showId)) {
//            unbooked those seat which are accidentally booked by isBooked method
            bookedSeatFalse(seatListFromTicketRequestDto, showId);
            throw new Exception("seat is booked");
        }
//        string of seatNo
        String allottedSeatForTicketEntity = fillAllotedSeat(seatListFromTicketRequestDto);
//        List of showSeat
        List<ShowSeat> bookedShowSeatForTicketEntity = fillBookedSeat(seatListFromTicketRequestDto, showId);
//        getting price of booked seats and filling allottedSeatForTicketEntity
        double price = calculateBookingAmount(seatListFromTicketRequestDto, showId);

//        Setting the atrribute of ticket bcs ticketConverter is throwing null exception
//        Ticket ticket = TicketConverter.convertDtoToEntity(ticketRequestDto);
        Ticket ticket = new Ticket();
        ticket.setBookedAt(new Date());
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setAmount((int) price);
        ticket.setBookedSeats(bookedShowSeatForTicketEntity);
        ticket.setAllottedSeats(allottedSeatForTicketEntity);
//        setting ticket attribute in showSeats
        for (ShowSeat showSeat : bookedShowSeatForTicketEntity) {
            showSeat.setTicket(ticket);
            showSeat.setBookedAt(new Date());
        }
//setting the tikcetList Atrribute of User and Show
        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);
//        userRepository.findById(ticketRequestDto.getUserId()).get().getTicketList().add(ticket);
//        showRepository.findById(ticketRequestDto.getShowId()).get().getTicketList().add(ticket);
//        saving the ticket
        userRepository.save(user);
//        showRepository.save(show);
//        ticketRepository.save(ticket);
        return price;
    }

    public String fillAllotedSeat(List<String> seatList) {
        String allottedSeat = "";
        int size = seatList.size(), i;
        for (i = 0; i < size - 1; i++) {
            allottedSeat += (seatList.get(i) + ",");
        }
        allottedSeat += seatList.get(i);
        return allottedSeat;
    }

    public List<ShowSeat> fillBookedSeat(List<String> seatList, int showId) {
        List<ShowSeat> showSeatList = new ArrayList<>();
        for (String seatNo : seatList) {
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo, showId);
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }

    private void bookedSeatFalse(List<String> seatList, int showId) {
        for (String seatNo : seatList) {
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo, showId);
            showSeat.setBooked(false);
        }
    }

    private boolean isSeatBooked(List<String> seatList, int showId) {
        for (String seatNo : seatList) {
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo, showId);
            if (showSeat.isBooked()) {
                return true;
            }
            showSeat.setBooked(true);
        }
        return false;
    }

    private double calculateBookingAmount(List<String> seatList, int showId) {
        double price = 0;
        for (String seatNo : seatList) {
            ShowSeat showSeat = showSeatRepository.getShowSeatBySeatNoAndShowId(seatNo, showId);
            price += showSeat.getRate();
        }
        return price;
    }


    public TicketCancelResponseDto cancelTickets(TicketCancelRequestDto ticketCancelRequestDto) throws Exception {
        int showId = ticketCancelRequestDto.getShowId();
        int userId = ticketCancelRequestDto.getUserId();
        int ticketId = ticketCancelRequestDto.getTicketId();
        if (userRepository.findById(userId).isEmpty() || showRepository.findById(showId).isEmpty() ||
                ticketRepository.findById(ticketId).isEmpty()) {
            throw new Exception("user,show or ticket does not exists :)");
        }
//        fetching ticket,show,user Object from repo
        Ticket cancelTicket = ticketRepository.findById(ticketId).get();
        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();
//        getting  booked List<ShowSeat> and allotted seats in string from cancel ticket
        List<ShowSeat> bookedSeatsFromCancelTicket = cancelTicket.getBookedSeats();
        String allottedSeatFromCancelTicket = cancelTicket.getAllottedSeats();
//        calculate refund after deducting 20% from the amount;
        double refund = getRefundAmount(cancelTicket.getAmount());
//        if user cancel the ticket, make that seats available
        makeShowSeatAvailable(bookedSeatsFromCancelTicket, showId);
//        removing the seat from allotedSeat and bookedSeat from ticket
//        removeTicketFromBookedAndAlloted(cancelticket,showId );
//        removing cancel ticket from user and show
        user.getTicketList().remove(cancelTicket);
        show.getTicketList().remove(cancelTicket);
//        saving the user entity
        userRepository.save(user);
        ticketRepository.deleteById(ticketId);
        return new TicketCancelResponseDto("ticket cancellation success .", refund);
    }

    //  When user cancel ticket then seat will set to be available
    private void makeShowSeatAvailable(List<ShowSeat> seatListFromCancelTicket, int showId) {
        for (ShowSeat showSeat : seatListFromCancelTicket) {
            showSeat.setTicket(null);
            showSeat.setBooked(false);
            showSeat.setBookedAt(null);

        }
    }

//calculating the refund amount
    public double getRefundAmount(int paidAmount) {
        double refund = 0.80 * paidAmount;
        return refund;
    }
}

