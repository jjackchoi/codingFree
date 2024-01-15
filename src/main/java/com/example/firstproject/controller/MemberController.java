package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String signup (){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        log.info(form.toString());
        // 1. dto -> 엔티티
        Member member = form.toEntity();
        log.info(member.toString());
        // 2. 리파지터리로 엔티티를 db에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/signup";
    }
}
