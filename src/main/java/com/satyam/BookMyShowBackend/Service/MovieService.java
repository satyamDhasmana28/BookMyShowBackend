package com.satyam.BookMyShowBackend.Service;

import com.satyam.BookMyShowBackend.Converter.MovieConverter;
import com.satyam.BookMyShowBackend.Model.Movie;
import com.satyam.BookMyShowBackend.Repository.MovieRepository;
import com.satyam.BookMyShowBackend.RequestDto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovieInDb(MovieRequestDto movieRequestDto) {
        Movie movie =  MovieConverter.convertDtoToEntity(movieRequestDto);
        movieRepository.save(movie);
        return movie.getName()+" is saved";
    }
}
