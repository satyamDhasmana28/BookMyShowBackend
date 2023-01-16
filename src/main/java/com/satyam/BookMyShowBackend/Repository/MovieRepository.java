package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
