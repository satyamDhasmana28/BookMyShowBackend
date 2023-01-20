package com.satyam.BookMyShowBackend.Model;

import com.satyam.BookMyShowBackend.Enum.SeatType;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "showseat_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private double rate;
    private boolean isBooked;
    private Date bookedAt;

//    Show can have multiple ShowSeat
    @ManyToOne
    @JoinColumn
    private Show show;
// Ticket
    @ManyToOne
    @JoinColumn
    private Ticket ticket;
}
