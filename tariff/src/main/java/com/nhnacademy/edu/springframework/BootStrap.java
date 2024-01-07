package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.config.JavaConfig;
import com.nhnacademy.edu.springframework.service.DataLoadService;
import com.nhnacademy.edu.springframework.service.WaterBillService;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        DataLoadService dataLoadService = context.getBean("defaultDataLoadService", DataLoadService.class);
        dataLoadService.load();

        WaterBillService waterBillService = context.getBean("defaultWaterBillService", WaterBillService.class);
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");

        String input;
        while ((input = sc.nextLine()) != null) {
            if (input.isEmpty()) {
                break;
            }

            try {
                int usage = Integer.parseInt(input);
                waterBillService.calculateTariff(usage);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 값을 입력하였습니다.");
            } finally {
                System.out.println();
            }
            System.out.print("> ");
        }

        sc.close();
    }
}
