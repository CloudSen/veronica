package com.umbrella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 测试requireJs
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-31
 * @version: 1.0
 */
@Controller
@RequestMapping("/jstest")
public class RequireJsController {
    @RequestMapping("/normal")
    public String loadJs(){
        return "normalLoadJs";
    }

    @RequestMapping("/require")
    public String requireJs(){
        return "requireLoadJs";
    }
}
