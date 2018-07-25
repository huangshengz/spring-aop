package cn.gzrrg.controller;

import cn.gzrrg.service.AspectService;
import cn.gzrrg.aspectj.AuthCheck;
import cn.gzrrg.service.NormalService;
import cn.gzrrg.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsz  2018/7/25 0025
 */
@RestController
public class TestController {

    @Autowired
    private AspectService aspectService;

    @Autowired
    private NormalService normalService;

    @Autowired
    private SomeService someService;

    @RequestMapping("/test")
    @AuthCheck
    public String test(){
        return  "成功访问咯！";
    }

    @RequestMapping("/testService")
    public void testService() {
        aspectService.logMethod("say love");
        try {
            aspectService.exceptionMethod();
        } catch (Exception e) {
        }
        normalService.normal();
    }

    @RequestMapping("/testTime")
    public void testTime(){
        someService.testTime();
    }
}
