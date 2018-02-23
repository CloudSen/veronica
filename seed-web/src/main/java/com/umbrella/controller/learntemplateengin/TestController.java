package com.umbrella.controller.learntemplateengin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * Description: learnrequirejs controller
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
}
