package com.satyam.BookMyShowBackend.Converter;

import com.satyam.BookMyShowBackend.Model.Show;
import com.satyam.BookMyShowBackend.RequestDto.ShowRequestDto;

public class ShowConverter {
    public static Show convertDtoToEntity(ShowRequestDto showRequestDto) {
        Show show = Show.builder().showDate(showRequestDto.getShowDate()).
                    showTime(showRequestDto.getShowTime()).build();
        return show;
    }
}
