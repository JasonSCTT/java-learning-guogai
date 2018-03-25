// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TestOperator.java

import java.io.PrintStream;

public class TestOperator
{

    public TestOperator()
    {
    }

    public static void main(String args[])
    {
        int i = 4;
        int j = i++;
        int k = ++i;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        byte byte0 = 8;
        byte byte1 = 4;
        System.out.println(byte0 & byte1);
        System.out.println(byte0 | byte1);
        System.out.println(~byte0);
        System.out.println(byte0 ^ byte1);
        String s = "5";
        byte byte2 = 4;
        System.out.println((new StringBuilder()).append(s).append(byte2).toString());
    }
}
