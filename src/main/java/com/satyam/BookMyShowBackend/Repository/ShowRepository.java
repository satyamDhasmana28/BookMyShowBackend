package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
