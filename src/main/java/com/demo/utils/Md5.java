package com.demo.utils;

/**
 * Created by simpletour_java on 2015/6/3.
 */
public class Md5 {

    //生成MD5摘要的算法
    public static String messageDigest(String text) {
        String temp="";

        try {

            java.security.MessageDigest alga=java.security.MessageDigest.getInstance("MD5");
            alga.update(text.getBytes());
            byte[] aDigest=alga.digest();
            temp = byte2hex(aDigest); //将字符串转换成二进制
            return temp;
        }
        catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        }
        return temp;
    }

    // 二行制转字符串，生成摘要的时候用到了这个
    private static String byte2hex(byte[] b)
    {
        String hs="";
        String temp="";
        for (int n=0;n<b.length;n++)
        {
            temp=(Integer.toHexString(b[n] & 0XFF)); //这里是将字符串转换成16进制的字符
            if (temp.length()==1) hs=hs+"0"+temp;
            else hs=hs+temp;
            if (n<b.length-1)  hs=hs+"";
        }
        return hs.toUpperCase();
    }

}
