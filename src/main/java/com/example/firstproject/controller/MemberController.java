package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    // 회원 등록 화면
    @GetMapping("/signup")
    public String signup (){
        return "members/new";
    }

    // 회원 등록
    @PostMapping("/join")
    public String join(MemberForm form){
        log.info(form.toString());
        // 1. dto -> 엔티티
        Member member = form.toEntity();
        log.info(member.toString());
        // 2. 리파지터리로 엔티티를 db에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    // 회원 상세 조회
    @GetMapping("/members/{id}")
    public String findById(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    // 회원 전체 조회
    @GetMapping("/members")
    public String findAll(Model model){
        ArrayList<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/index";
    }

    // 회원 수정 페이지
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/edit";
    }

    // 회원 수정
    @PostMapping("/members/update")
    public String update(MemberForm form){
        log.info(form.toString());
        // dto -> 엔티티
        Member memberEntity = form.toEntity();
        // 정보 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 존재여부 확인 후 데이터 수정
        if (target != null){
           memberRepository.save(memberEntity);
        }
        // 리다이렉트 후 수정 값 확인
        return "redirect:/members/" + memberEntity.getId() ;
    }
}
