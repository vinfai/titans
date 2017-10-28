package com.titans.avatar.web.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vinfai
 * @since 2017/6/7
 */
@RestController
public class SearchController {

    @RequestMapping("/search.xhtml")
    public String search(@RequestParam String name) {
        System.out.println("request name g :" + name);
        return "hello,"+name;
    }
}
