package com.satyam.BookMyShowBackend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "show_table")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
//    Movie
    @ManyToOne
    @JoinColumn
    private Movie movie;
//   Theatre
   @ManyToOne
   @JoinColumn
   private Theatre theatre;
//   ShowSeat
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
//    @JoinColumn
    List<ShowSeat> showSeatList;

//    Ticket
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
//    @JoinColumn
    List<Ticket> ticketList;
}

