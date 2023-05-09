package com.example.xmall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        return page;
    }

//    @GetMapping("/confirm.html")
//    public String confirmPage() {
//        return "confirm";
//    }
//
//
//    @GetMapping("/list.html")
//    public String listPage() {
//        return "list";
//    }
//
//    @GetMapping("/detail.html")
//    public String detailPage() {
//        return "detail";
//    }
}
