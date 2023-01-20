package com.satyam.BookMyShowBackend.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCancelResponseDto {
    private String message;
    private double refundAmount;
}
