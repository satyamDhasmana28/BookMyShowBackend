package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
    @Query(value = "select * from show_table s where s.show_date >= ?1 and s.show_time >= ?2 and s.show_date <= ?3 and s.show_time <= ?4 ",
            nativeQuery = true
    )
    List<Show> getByStartDateAndEndDate(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);
}
