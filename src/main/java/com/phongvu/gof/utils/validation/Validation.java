package com.phongvu.gof.utils.validation;

import java.util.Scanner;

public class Validation {

    private Validation() {
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int getInteger(String msg, int min, int max) {
        while (true) {
            try {
                //thong bao yeu cau nhap dau vao
                System.out.print(msg);
                int number = Integer.parseInt(scanner.nextLine());

                //kiem tra so co nam trong khoang gia tri hay khong
                if (number >= min && number <= max) {
                    return number;
                } else {
                    //In ra thong bao loi
                    System.err.println("Please input number in [" + min + ", " + max + "]");
                }
            } catch (NumberFormatException e) {
                //Hien thi thong bao loi
                System.err.println("Please input integer number");
            }
        }
    }

    public static String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String str = scanner.nextLine();
            if (!str.isEmpty())
                return str;
            else
                System.out.println("Please input String!");
        }
    }


    public static boolean getYN(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;
            System.out.println("Please input Y or N!");
        }
    }


}
