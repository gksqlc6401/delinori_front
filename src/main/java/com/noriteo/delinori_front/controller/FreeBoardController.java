package com.noriteo.delinori_front.controller;

import com.noriteo.delinori_front.dto.FreeBoardDTO;
import com.noriteo.delinori_front.dto.PageRequestDTO;
import com.noriteo.delinori_front.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("responseDTO", freeBoardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {

    }

//    @PostMapping("/register")
//    public void register(FreeBoardDTO freeBoardDTO, RedirectAttributes redirectAttributes) {
//        Long bno = freeBoardService.register(freeBoardDTO);
//
//        redirectAttributes.addFlashAttribute("result", bno);
//
//        return "redirect:/board/list";
//    }

    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        model.addAttribute("dto",freeBoardService.read(bno));
    }
}
