package com.js.json.jackson;

import java.util.Date;

/**
 * Created by Administrator on 2018-1-5.
 */
public class Student {

    int Id;
    String name;
    Date birthDay;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
