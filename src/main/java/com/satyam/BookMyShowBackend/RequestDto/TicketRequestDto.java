package com.satyam.BookMyShowBackend.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDto {
    private int userId;
    private int showId;
    private List<String> seatList=new ArrayList<>();
}
