package com.example.demo_spring_mvc;

public class Hello {
    private String name;

    public Hello(String name) {
        this.name = name;
    }

    public Hello() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
