package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;


    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() != null)
            return null;
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeForm dto) {
        // 1. dto -> 엔티티
        Coffee coffee = dto.toEntity();
        log.info("id: {}, coffee: {}", id, coffee.toString());
        // 2. 타겟 확인
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. 잘못된 응답 처리
        if (target == null || id != coffee.getId()){
            log.info("잘못된 응답! id: {}, coffee: {}", id, coffee.toString());
            return null;
        }
        // 4. 성공 응답 처리
        target.patch(coffee); // null 방지
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null){
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Coffee> createCoffees(List<CoffeeForm> dtos) {
        // 1. 여러개의 dto를 엔티티묵음으로 변환
        List<Coffee> coffeeList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티묶음을 db에 저장
        coffeeList.stream()
                .forEach(coffee -> coffeeRepository.save(coffee));
        // 3. 강제 예외 발생시키기
        coffeeRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("실패"));
        // 4. 결과 값 반환
        return coffeeList;
    }
}
