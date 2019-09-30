package com.example.springbootbill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BillAction {
    @RequestMapping("/demo")
    public String test(){
        return "bill/list.html";
    }
}
