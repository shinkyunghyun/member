package com.example.memberjoin.controller;

import com.example.memberjoin.dto.MemberDTO;
import com.example.memberjoin.entity.MemberEntity;
import com.example.memberjoin.repository.MemberRepository;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/member")
    public String memberJoin(){
        return "member";
    }

    //데이터 입력
    @PostMapping("/join/create")
    public String createMember(MemberDTO memberDTO){
//        System.out.println(memberDTO.toString());
        log.info(memberDTO.toString());
        MemberEntity memberEntity = memberDTO.toEntity();
//        System.out.println(memberEntity.toString());
        log.info(memberEntity.toString());
        MemberEntity saved = memberRepository.save(memberEntity);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/onelist/"+saved.getId();
    }
//단일 데이터 조회
    @GetMapping("/onelist/{id}")
    public String findmember(@PathVariable Long id, Model model){
        MemberEntity onelist = memberRepository.findById(id).orElse(null);
    model.addAttribute("onelist",onelist);
        return "onelist";
    }
//전체 데이터 조회
    @GetMapping("/memlist")
    public String allmember(Model model){
        List<MemberEntity> alllist = memberRepository.findAll();
        model.addAttribute("memberList",alllist);
        return "memlist";
    }

    //데이터 수정
    @GetMapping("/onelist/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        MemberEntity editEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("editmem",editEntity);
        return "edit";
    }
    @PostMapping("/join/update")
    public String update(MemberDTO updateDTO)  {
        log.info(updateDTO.toString());
        MemberEntity updateEntity = updateDTO.upEntity();
        log.info(updateEntity.toString());
        MemberEntity updateSaved = memberRepository.findById(updateEntity.getId()).orElse(null);
        if(updateSaved != null){
            updateSaved = memberRepository.save(updateEntity);
        }
        log.info(updateSaved.toString());
        return "redirect:/onelist/"+updateSaved.getId();
    }

    //삭제하기
    @GetMapping("/onelist/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        MemberEntity deleteEntity = memberRepository.findById(id).orElse(null);
        log.info(deleteEntity.toString());
        if(deleteEntity != null){
            memberRepository.delete(deleteEntity);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }

        return "redirect:/memlist";
    }
}
