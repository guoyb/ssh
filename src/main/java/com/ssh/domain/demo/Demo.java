package com.ssh.domain.demo;

import javax.persistence.*;

/**
 * Created by guoyibin on 15/1/30.
 */
@Entity
@Table(name = "demo")
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public Demo() {
    }

    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Demo)) return false;

        Demo demo = (Demo) o;

        if (age != demo.age) return false;
        if (!id.equals(demo.id)) return false;
        if (!name.equals(demo.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
