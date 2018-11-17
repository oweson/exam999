package top.sea521.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/22 0022 19:35
 */
@RestController
public class TestHotDeploy {
    @RequestMapping("/testhot")
    public Object hot() {
        return "hot is success hot is useful";
    }
}
