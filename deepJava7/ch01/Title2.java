// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Title.java

import java.io.PrintStream;

public class Title
{

    public Title()
    {
    }

    public static void main(String args[])
    {
        System.out.println(generate("\u90ED\u5C0F\u65ED", "\u7537"));
    }

    public static String generate(String s, String s1)
    {
        String s2 = "";
        String s3 = s1;
        byte byte0 = -1;
        switch(s3.hashCode())           //这里被替换为对应的哈希值。
        {   
        case 30007:                     //变为原来字符串常量的值
            if(s3.equals("\u7537"))     //这里需要进行判断，因为哈希在映射的时候可能发生冲突，所以需要String类的equels()方法进行比较。
                byte0 = 0;              //保证转换之后的代码逻辑与之前的一样。
            break;

        case 22899: 
            if(s3.equals("\u5973"))
                byte0 = 1;
            break;
        }
        switch(byte0)                   //根据上面的byte类型进行重新switch，
        {
        case 0: // '\0'
            s2 = (new StringBuilder()).append(s).append("\u5148\u751F").toString();          //+号在底层使用了StringBuilder类进行apped操作。
            break;

        case 1: // '\001'
            s2 = (new StringBuilder()).append(s).append("\u5973\u58EB").toString();
            break;

        default:
            s2 = s;
            break;
        }
        return s2;
    }
}
