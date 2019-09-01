package xyz.goenitz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @ResponseBody
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteUser();
    }
}
