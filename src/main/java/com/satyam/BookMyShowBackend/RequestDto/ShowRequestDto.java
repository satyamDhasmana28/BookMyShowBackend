package com.satyam.BookMyShowBackend.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequestDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private int movieId;
    private int theatreId;
//    this will be multiplied by theatre seat rate to fix the final price of that show seat
    private double multiplier;
}
