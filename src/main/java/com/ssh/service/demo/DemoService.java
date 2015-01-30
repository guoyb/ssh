package com.ssh.service.demo;

import com.ssh.domain.demo.Demo;

import java.util.List;

/**
 * Created by guoyibin on 15/1/30.
 */
public interface DemoService {

    public List<Demo> getAll();

    public void add(Demo demo);

    public void deleteById(Long id);

    public Demo getById(Long id);

    public void update(Demo demo);
}
