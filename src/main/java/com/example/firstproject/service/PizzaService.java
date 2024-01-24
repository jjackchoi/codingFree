package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    public List<PizzaDto> pizzas() {
        // 피자 조회
        List<Pizza> pizzas = pizzaRepository.findAll();

        // 엔티티 -> dto
        List<PizzaDto> dtos = new ArrayList<PizzaDto>();

        for (int i = 0; i < pizzas.size(); i++){
            Pizza p = pizzas.get(i);
            PizzaDto dto = PizzaDto.createPizzaDto(p);
            dtos.add(dto);
        }

        return dtos;
    }

    public PizzaDto onePizza(Long id) {
        // 피자 조회
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        // 엔티티 -> dto
        PizzaDto dto = PizzaDto.createPizzaDto(pizza);
        // 반환
        return dto;
    }

    @Transactional
    public PizzaDto createPizza(PizzaDto dto) {
        // 피자 엔티티 생성
        Pizza pizza = Pizza.createPizza(dto);
        // 피자 엔티티를 db에 저장
        Pizza created = pizzaRepository.save(pizza);
        // 엔티티 -> dto 후 반환
        return PizzaDto.createPizzaDto(created);
    }

    @Transactional
    public PizzaDto updatePizza(PizzaDto dto, Long id) {
        // 피자 조회 및 예외발생
        Pizza target = pizzaRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("수정 실패! " +
                        "피자가 없습니다"));
        // 업데이트
        target.patch(dto);
        // db에 저장
        Pizza updated = pizzaRepository.save(target);
        // 엔티티를 dto로 변환 및 반환
        return PizzaDto.createPizzaDto(updated);
    }

    @Transactional
    public PizzaDto deletePizza(Long id) {
        // 피자 조회 및 예외 발생
        Pizza target = pizzaRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("삭제 실패!" +
                        "피자가 없습니다"));
        // 삭제
        pizzaRepository.delete(target);
        // 반환
        return PizzaDto.createPizzaDto(target);
    }
}
