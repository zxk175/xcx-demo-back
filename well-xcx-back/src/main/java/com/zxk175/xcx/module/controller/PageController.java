package com.zxk175.xcx.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zxk175
 * @since 2019-09-19 17:57:31
 */
@ApiIgnore
@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "redirect:/swagger-ui.html";
    }

}
