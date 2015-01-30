package com.ssh.action.demo;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.domain.demo.Demo;

/**
 * Created by guoyibin on 15/1/30.
 */
@Scope("prototype")
@Controller
public class Get extends ActionSupport {
    @Autowired
    private DemoService demoService;

    public String execute() throws Exception{
        Demo demo = demoService.getById(1l);
        System.out.println(demo.getName());
        System.out.println(demo.getAge());
        return "demos";
    }
}
