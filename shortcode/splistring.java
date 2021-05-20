import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        String line = scanner.nextLine();
        String[] array = line.split(" ");
        for (String s : array) {
            System.out.print((Integer.parseInt(s) - 1) + " ");
        }
    }
}
