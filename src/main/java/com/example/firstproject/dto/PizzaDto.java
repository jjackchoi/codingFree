package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PizzaDto {

    private Long id;
    private String name;
    private Long price;

    public static PizzaDto createPizzaDto(Pizza pizza) { // static은 객체생성 없이 사용 가능
        return new PizzaDto(
                pizza.getId(),
                pizza.getName(),
                pizza.getPrice()
        );
    }
}
