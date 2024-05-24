import java.util.Scanner;

public class convertBinaryToDecimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("PLEASE INSERT A BINARY NUMBER OF YOUR CHOICE   : ");
        String Binary_Number = scanner.nextLine();
        convertBinaryToDecimal(Binary_Number); // convert the binary number to decimal
        scanner.close();
    }

    public static void convertBinaryToDecimal(String Binary_Number) {
        if (!isValidBinary(Binary_Number)) {

            System.out.println("Input not valid");
            return;
        }

        int decimal = 0;
        for (int i = 0; i < Binary_Number.length(); i++) {

            if (Binary_Number.charAt(i) == '1') {
                decimal += Math.pow(2, Binary_Number.length() - 1 - i);
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("HERE IS THE RESULT AFTER CONVERTING BINARY NUMBER TO A DECIMAL NUMBER  :   " + decimal);
    }

    public static boolean isValidBinary(String Binary_Number) {
        for (int i = 0; i < Binary_Number.length(); i++) {
            char digit = Binary_Number.charAt(i);
            if (digit != '0' && digit != '1') {
                return false;

            }
        }
        return true;
    }
}
