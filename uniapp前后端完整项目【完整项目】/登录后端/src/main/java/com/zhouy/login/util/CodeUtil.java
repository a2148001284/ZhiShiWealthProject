package com.zhouy.login.util;

import java.util.Random;

public class CodeUtil {

    public static String randomCode(){
        StringBuilder stringBuilder =new StringBuilder();
        Random random =new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
