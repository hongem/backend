package com.nuce.service_gara.controller.OnlyToTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestNotify {


    @GetMapping("/")
    public String test() {
        return "index";
    }


}
