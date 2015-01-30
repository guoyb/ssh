package com.ssh.action.demo;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.domain.demo.Demo;
import com.ssh.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by guoyibin on 15/1/30.
 */
@Scope("prototype")
@Controller
public class Demos extends ActionSupport {

    @Autowired
    private DemoService demoService;

    public String execute() throws Exception{
        List<Demo> demos = demoService.getAll();
        for (Demo demo:demos){
            System.out.println(demo.getAge());
            System.out.println(demo.getName());
        }
        return SUCCESS;
    }

}
