package com.freemarker.fm;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public User(int id, String name, int age,Group group) {
    	this.id = id;
    	this.name = name;
    	this.age = age;
    	 this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
