package com.satyam.BookMyShowBackend.Model;

//import jakarta.persistence.*;

//import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;
    private Date releaseDate;
//    show
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<Show>  listOfShow=new ArrayList<>();
}
