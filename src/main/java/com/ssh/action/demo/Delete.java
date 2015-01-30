package com.ssh.action.demo;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by guoyibin on 15/1/30.
 */
@Scope("prototype")
@Controller
public class Delete extends ActionSupport {
    @Autowired
    private DemoService demoService;

    public String execute() throws Exception{
        demoService.deleteById(1l);
        return "demos";
    }
}
