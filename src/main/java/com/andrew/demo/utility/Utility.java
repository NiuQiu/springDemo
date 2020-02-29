package com.andrew.demo.utility;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.andrew.demo.utility.Type.*;

public class Utility {
    public static UUID generateUUID(){
        return UUID.randomUUID();
    }

    public static <T extends Object> List<T> convert(T[] arr) {
        return Arrays.stream(arr).collect(Collectors.toList());
    }

    public static String getType(String s){
        switch(s){
            case "romance":
                return NONFICTION.getType();
            case "autobiography":
                return NONFICTION.getType();
            case "science":
                return NONFICTION.getType();
            default:
                return FICTION.getType();
        }
    }

    public static String capitalLetter(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
