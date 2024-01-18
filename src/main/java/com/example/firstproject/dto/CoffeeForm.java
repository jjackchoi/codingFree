package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CoffeeForm {
    private Long id;
    private String name;
    private Long price;

    public Coffee toEntity(){
        return new Coffee(id, name, price);
    }
}
