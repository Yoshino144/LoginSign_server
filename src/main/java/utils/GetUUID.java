package utils;

import java.util.UUID;

public class GetUUID {
    public static String get(){
        String uuid="";
        uuid= UUID.randomUUID().toString();
        System.out.println("getUUID:"+uuid);
        return uuid;
    }
}
