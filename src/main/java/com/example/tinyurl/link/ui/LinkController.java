package com.example.tinyurl.link.ui;

import com.example.tinyurl.link.dto.LinkRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 사용자 링크 요청을 처리한다.
 */
@Controller
public class LinkController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("link", new LinkRequestDto());
        return "index";
    }
}
