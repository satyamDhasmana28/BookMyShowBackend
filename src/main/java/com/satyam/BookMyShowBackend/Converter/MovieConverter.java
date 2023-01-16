package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.Movie;
import com.satyam.BookMyShowBackend.RequestDto.MovieRequestDto;

public class MovieConverter {
    public static Movie convertDtoToEntity(MovieRequestDto movieRequestDto) {
        Movie movie = Movie.builder().name(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).
                      releaseDate(movieRequestDto.getReleaseDate()).build();
        return movie;
    }
}
