package com.satyam.BookMyShowBackend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatre_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private String address;

//    list of Show
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    List<Show> showList = new ArrayList<>();
//    list of TheaterSeats
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
//    @JoinColumn
    List<TheatreSeat> theatreSeatList;

}
