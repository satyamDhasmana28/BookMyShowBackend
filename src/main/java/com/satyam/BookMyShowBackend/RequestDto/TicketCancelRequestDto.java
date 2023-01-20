package com.satyam.BookMyShowBackend.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCancelRequestDto {
    private int UserId;
    private int ShowId;
//    private List<String> cancelTicketArrayList =new ArrayList<>();
    private  int ticketId;
}
