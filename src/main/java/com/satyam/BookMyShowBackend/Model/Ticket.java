package com.satyam.BookMyShowBackend.Model;

//import jakarta.persistence.*;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket_table")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String allottedSeats;
    private int amount;
    private Date bookedAt;
//    joining Ticket with User as user can book multiple ticket
    @ManyToOne
    @JoinColumn
    private User user;
//    joining Show with Ticket as one show can correspond to multiple Ticket
    @ManyToOne
    @JoinColumn
    private Show show;

//    ShowSeat
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
//    @JoinColumn
    private List<ShowSeat> bookedSeats;
}
