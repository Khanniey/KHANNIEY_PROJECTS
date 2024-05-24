//Makhanani Mlambo
//4270025
//TERM 2 PRACTICAL 2

import java.util.Scanner;

public class convertDecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // I used scanner to allow user to insert his or her own decimal
                                                  // number
        System.out.print("PLEASE INSERT A DECIMAL NUMBER OF YOUR CHOICE  : ");
        int Decimal_Number = scanner.nextInt();
        convertDecimalToBinary(Decimal_Number); // convert decimal number to binary number
        scanner.close();
    }

    public static void convertDecimalToBinary(int n) {
        if (!(0 <= n && n <= 2047)) {
            System.out.println("Input not valid");
            return;
        }
        StringBuilder Binary = new StringBuilder("");
        if (n == 0) {
            Binary.append(0);
        } else {
            while (n > 0) {
                Binary.insert(0, n % 2);
                n /= 2;
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("HERE IS THE RESULT AFTER CONVERTING DECIMAL NUMBER TO A BINARY NUMBER : " + Binary);
    }
}
