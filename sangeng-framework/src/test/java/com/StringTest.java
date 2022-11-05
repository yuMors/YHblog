package com;

import org.junit.jupiter.api.Test;

public class StringTest {

  /*  public static void main(String[] args) {
        *//*String Str = new String("菜鸟教程：www.runoob.com");
        boolean retVal;

        retVal = Str.endsWith( "runoob" );
        System.out.println("返回值 = " + retVal );

        retVal = Str.endsWith( "com" );
        System.out.println("返回值 = " + retVal );*//*

        String Str = new String("菜鸟教程");
        boolean retVal;

        retVal = Str.endsWith( "程" );
        System.out.println("返回值 = " + retVal );

        retVal = Str.endsWith( "教程" );
        System.out.println("返回值 = " + retVal );

        retVal = Str.endsWith( "教" );
        System.out.println("返回值 = " + retVal );
    }*/

    @Test
    public void test01(){
        String Str = "菜鸟教程";
        boolean ret;
        ret = Str.endsWith( "程" );
        System.out.println("返回值 = " + ret );

        ret = Str.endsWith( "教程" );
        System.out.println("返回值 = " + ret );

        ret = Str.endsWith( "教" );
        System.out.println("返回值 = " + ret );
    }
}
