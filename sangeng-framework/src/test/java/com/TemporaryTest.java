package com;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TemporaryTest {
    @Test
    public void localDateTimeTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
    @Test
    public void dateTest(){
        Date date = new Date();
        System.out.println(date);
    }
    @Test
    public void dateTest01(){
        System.out.println(LocalDate.now());
    }

}

