package com.mongocourse.firstweek;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created with IntelliJ IDEA.
 * User: draileanu
 * Date: 14/01/14
 * Time: 14:02
 */
public class HelloSpark {

    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hi Diana";
            }
        });
    }
}
