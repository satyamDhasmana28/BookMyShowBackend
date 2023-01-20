package com.satyam.BookMyShowBackend.Model;

//import jakarta.persistence.*;
import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "mobile_number")
    private String mobile;
    @CreationTimestamp
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdOn;

//    list of ticket
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Ticket> ticketList;
}
