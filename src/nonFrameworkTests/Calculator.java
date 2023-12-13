package nonFrameworkTests;

 import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double number1 = scanner.nextDouble();

        System.out.print("Enter the operator (+, -, *, /): ");
        String operator = scanner.next();

        System.out.print("Enter the second number: ");
        double number2 = scanner.nextDouble();

        double result = 0;

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                    return;
                }
                result = number1 / number2;
                break;
            default:
                System.out.println("Invalid operator.");
                return;
        }

        System.out.println(number1 + " " + operator + " " + number2 + " = " + result);
    }
}

