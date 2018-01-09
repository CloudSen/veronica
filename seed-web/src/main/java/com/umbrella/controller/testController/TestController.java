package com.umbrella.controller.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Description: test controller
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-09
 * @version: 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/template")
    public String test(ModelMap map) {
        map.addAttribute("name", "cloudsen");
        map.addAttribute("bookTitle", "spring boot");
        return "welcome";
    }

    @RequestMapping("/index")
    public String test2() {
        return "index";
    }
}
