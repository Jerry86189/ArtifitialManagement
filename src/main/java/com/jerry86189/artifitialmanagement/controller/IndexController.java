package com.jerry86189.artifitialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: IndexController
 * Description: TODO
 * date: 2023/06/10 21:49
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        System.out.println("index page");
        return "forward:/login.html";
    }
}
