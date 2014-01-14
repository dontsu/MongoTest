package com.mongocourse.firstweek;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: draileanu
 * Date: 14/01/14
 * Time: 14:26
 */
public class HelloFreemarker {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloFreemarker.class, "/");
        try {
            Template template = config.getTemplate("hello.html");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "aaa");
            StringWriter writer = new StringWriter();
            template.process(map, writer);
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
