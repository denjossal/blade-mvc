package com.dsdev.blade.mvc.model;

import com.hellokaton.anima.Model;
import com.hellokaton.anima.annotation.Column;
import com.hellokaton.anima.annotation.Table;

@Table(name = "user")
public class User extends Model {

    private String id;
    @Column(name = "user_name")
    private String username;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}