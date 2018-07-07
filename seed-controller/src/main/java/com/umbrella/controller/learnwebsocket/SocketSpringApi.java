package com.umbrella.controller.learnwebsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring Socket API 控制器
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-23
 */
@Controller
@RequestMapping(value = "/socket")
public class SocketSpringApi {

    @RequestMapping(value = "/h5socket")
    public String toH5SocketPage() {
        return "learnwebsocket/h5socket";
    }
}
