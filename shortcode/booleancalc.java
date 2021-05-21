import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();

        boolean check1 = num1 + num2 == 20;
        boolean check2 = num2 + num3 == 20;
        boolean check3 = num1 + num3 == 20;

        boolean result = check1 || check2 || check3;
        System.out.println(result);
    }
}