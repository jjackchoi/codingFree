package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PizzaApiController {

    private final PizzaService pizzaService;

    // 전체 조회
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> pizzas(){
        // 서비스에 위임
        List<PizzaDto> dtos = pizzaService.pizzas();
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 단건 조회
    @GetMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> onePizza(@PathVariable Long id){
        // 서비스에 위임
        PizzaDto dto = pizzaService.onePizza(id);
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 생성
    @PostMapping("/api/pizzas")
    public ResponseEntity<PizzaDto> createPizza(@RequestBody PizzaDto dto){
        // 서비스에 위임
        PizzaDto created = pizzaService.createPizza(dto);
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 수정
    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@RequestBody PizzaDto dto,
                                                @PathVariable Long id) {
        // 서비스에 위임
        PizzaDto updated = pizzaService.updatePizza(dto, id);
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 삭제
    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> deletePizza(@PathVariable Long id){
        // 서비스에 위임
        PizzaDto deleted = pizzaService.deletePizza(id);
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
