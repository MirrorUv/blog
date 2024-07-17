package com.mirror.utils;

import cn.hutool.crypto.digest.BCrypt;
import com.mirror.common.Constants;

public class BCryptUtil {
    public static String enCoding(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean verify(String password,String enCodedPassword){
        return BCrypt.checkpw(password,enCodedPassword);
    }
}
