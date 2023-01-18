package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Show;
import com.satyam.BookMyShowBackend.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {
    @Query(value = "SELECT * FROM showseat_table s WHERE s.seat_no=?1 and s.show_id=?2",
            nativeQuery = true)
    public ShowSeat getShowSeatBySeatNoAndShowId(String SeatNo, int showId);
}
