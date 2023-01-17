package com.satyam.BookMyShowBackend.RequestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {
    String mobile;
    String name;
    int id;
}
