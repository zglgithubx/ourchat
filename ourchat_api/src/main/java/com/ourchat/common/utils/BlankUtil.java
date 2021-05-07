package com.ourchat.common.utils;

public class BlankUtil {
    public static boolean isBlank(String ...strings){
        for(String str:strings){
            if (str == null || str.trim().length() == 0){
                return true;
            }
        }
        return false;
    }
}
