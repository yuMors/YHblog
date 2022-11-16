package com;

import com.sangeng.constants.YHTime;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.poi.ss.formula.functions.Now;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class TemporaryTest {
    @Test
    public void localDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    public void dateTest01() {
        System.out.println(LocalDate.now());
    }

    @Test
    public void nowTimeAll() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.now().format(formatter));
    }

    @Test
    public void nowTimeHMS() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(LocalDateTime.now().format(formatter));

    }

    @Test
    public void nowTimeYMD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(LocalDateTime.now().format(formatter));
    }

    @Test
    public void nowTimeYMD01() {

        String dataName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(dataName);
    }

    @Test
    public void nowTimeYMD02() {
        for (int i = 1; i <= 3; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            String dataName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String uuidNew1 = dataName + uuid.substring(15);
            String uuidNew3 = dataName + "-" + uuid.substring(16);
            String uuidNew4 = dataName + "YH" + "-" + uuid.substring(18);

            System.out.println(uuid);
            System.out.println(uuidNew1);
            System.out.println(uuidNew3);
            System.out.println(uuidNew4);
            System.out.println(UUID.randomUUID());
            System.out.println();
        }
    }

    @Test
    public void nowTimeYMD03() {
        for (int i = 1; i <= 3; i++) {
            UUID uuid = UUID.randomUUID();
            String uuidNew1 = uuid.toString();
            String uuidNew2 = uuid.toString().replaceAll("-", "");


            System.out.println(uuid);
            System.out.println(uuidNew1);
            System.out.println(uuidNew1.length());
            System.out.println(uuidNew2);
            System.out.println(uuidNew2.length());

            System.out.println();
        }
    }

    @Test
    public void nowTimeYMD04() {
        System.out.println(YHTime.now());
        YHTime time = new YHTime();
        System.out.println(time.nowTest());
    }

    public static void main(String[] args) {
        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break ok;
                }

            }
        }
    }

    @Test
    public void test117() {
        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break ok;
                }
            }
        }
    }
    @Test
    public void test129() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    System.lineSeparator();
                    break;
                }
            }
        }
    }
}

