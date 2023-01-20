package com.satyam.BookMyShowBackend.Controller;

import com.satyam.BookMyShowBackend.RequestDto.TicketCancelRequestDto;
import com.satyam.BookMyShowBackend.RequestDto.TicketRequestDto;
import com.satyam.BookMyShowBackend.ResponseDto.TicketCancelResponseDto;
import com.satyam.BookMyShowBackend.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<Double> bookTicket(@RequestBody TicketRequestDto ticketRequestDto) throws Exception {
    Double bookingAmount = ticketService.bookTickets(ticketRequestDto);
    return new ResponseEntity<>(bookingAmount, HttpStatus.OK);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<TicketCancelResponseDto> cancelTicket(@RequestBody
                                                                    TicketCancelRequestDto ticketCancelRequestDto) throws Exception {
        TicketCancelResponseDto ticketCancelResponseDto = ticketService.
                                                          cancelTickets(ticketCancelRequestDto);
        return new ResponseEntity<>(ticketCancelResponseDto,HttpStatus.OK);
    }

}
