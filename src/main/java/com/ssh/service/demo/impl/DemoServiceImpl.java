package com.ssh.service.demo.impl;

import com.ssh.dao.demo.DemoDao;
import com.ssh.domain.demo.Demo;
import com.ssh.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guoyibin on 15/1/30.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    public List<Demo> getAll() {
        return demoDao.getAll();
    }

    public void add(Demo demo) {
        demoDao.save(demo);
    }

    public void deleteById(Long id) {
        demoDao.deleteById(id);
    }

    public Demo getById(Long id) {
        return demoDao.getById(id);
    }

    public void update(Demo demo) {
        demoDao.update(demo);
    }
}
