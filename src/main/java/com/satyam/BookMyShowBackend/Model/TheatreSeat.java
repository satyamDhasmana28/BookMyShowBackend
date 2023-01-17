package com.satyam.BookMyShowBackend.Model;

import com.satyam.BookMyShowBackend.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theatreseat_table")
@Getter
@Setter
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    private double rate;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    //    theatre
    @ManyToOne
    @JoinColumn
    private Theatre theatre;
//
    public TheatreSeat(String seatno, int rate, SeatType seatType) {
        this.seatNo=seatno;
        this.rate=rate;
        this.seatType=seatType;
    }
}
