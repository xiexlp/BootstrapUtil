package com.js.json.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.json.jackson.UserAnno;

public class JacksonAnnoDemo {

    public static void main(String[] args) throws ParseException, IOException {
        UserAnno user = new UserAnno();
        user.setName("小民");
        user.setEmail("xiaomin@sina.com");
        user.setAge(20);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        //输出结果：{"name":"小民","birthday":"1996年09月30日","mail":"xiaomin@sina.com"}  

        Map m = mapper.readValue(json, Map.class);
        System.out.println(m.get("name"));

        //测试03：map--json
        json=mapper.writeValueAsString(m); //map转json
        System.out.println(json); //与之前格式完全相同，说明经过map转换后，信息没有丢失

        //测试04：json--对象
        UserAnno u=mapper.readValue(json, UserAnno.class); //json转java对象。经测，转成对象后，一切恢复正常
        System.out.println(u.getName());


        //变成二进制
        byte[] data=mapper.writeValueAsBytes(u);

        UserAnno userAnno = mapper.readValue(data,UserAnno.class);
        System.out.println(userAnno.getName());



    }
}  