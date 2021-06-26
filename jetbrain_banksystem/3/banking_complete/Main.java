package banking;

//import org.sqlite.core.DB;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        HashMap<String, String> AccMap =
//                new HashMap<String, String>();
        SQLITEDB.initalizeDB(args[1]);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            System.out.println();
            int choose = scanner.nextInt();
            if (choose==1){
                Acc.generateAcc(args[1]);
            }
            else if (choose==2){
                Acc.searchAcc(args[1]);
            }
            else if (choose == 0) {
                break;
            }

        }
//        Acc.generateAcc(args[1]);
//        Acc.searchAcc(args[1]);

    }
}



class Acc {
    static void generateAcc(String DBfilename) {
        int id = 0;
        while (true) {
            int customerAccNum1 = RandomNumbersDemo.randomGenerate(10000, 99999);
            int customerAccNum2 = RandomNumbersDemo.randomGenerate(1000, 9999);
            String customerAccNum = String.format("400000" + customerAccNum1 + customerAccNum2);
            int lastnum=Luhnalgorithm.lastnumGenerate(customerAccNum);
            customerAccNum = String.format(customerAccNum+lastnum);
            String pin = Integer.toString(RandomNumbersDemo.randomGenerate(1000, 9999));
            if (SQLITEDB.selectbalance(customerAccNum,DBfilename)!= -1){
                SQLITEDB.insert(id,customerAccNum,pin,0,DBfilename);
                System.out.println("Your card has been created");
                System.out.println("Your card number:");
                System.out.println(customerAccNum);
                System.out.println("Your card PIN:");
                System.out.println(pin);
                break;
            }
        }
    }
    static void searchAcc(String DBfilename) {
        Scanner accinput = new Scanner(System.in);
        System.out.println("Enter your card number:");
        String accNum=accinput.next();
        System.out.println("Enter your PIN:");
        String pin=accinput.next();
        if (pin.equals(SQLITEDB.selectpin(accNum,DBfilename))){
            System.out.println("You have successfully logged in!");
            System.out.println();
            while (true){
                System.out.println("1. Balance");
                System.out.println("2. Log out");
                System.out.println("0. Exit");
                System.out.println();
                int choose=accinput.nextInt();
                if (choose==1){
                    System.out.println("Balance: 0");
                    System.out.println();
                }
                else if(choose==2){
                    System.out.println("You have successfully logged out!");
                    break;
                }
                else if(choose==0){
                    System.out.println("Bye!");
                    System.exit(0);
                }
            }
        }
        else {
            System.out.println("Wrong card number or PIN!");
        }
    }
}

class RandomNumbersDemo {
    static int randomGenerate(int lower,int upper) {
        Random random = new Random();
        int intervalLength = upper - lower + 1;
        return (random.nextInt(intervalLength) + lower);
    }
}

class Luhnalgorithm {
    static int lastnumGenerate(String cardnum) {
        int[] calcnum=new int[9];
        char[] ch = cardnum.toCharArray();
        //return Character.getNumericValue(ch[6]);
        for (int i=0;i<=8;i++){
            calcnum[i]=Character.getNumericValue(ch[i+6]);
        }
        //System.out.println(Arrays.toString(calcnum));
        for (int i=0;i<=8;i+=2){
            calcnum[i]*=2;
        }
        //System.out.println(Arrays.toString(calcnum));
        for(int i=0;i<=8;i++){
            if(calcnum[i]>=10){
                calcnum[i]=calcnum[i]-9;
            }
        }
        //System.out.println(Arrays.toString(calcnum));
        int calcusum=8;
        for(int i=0;i<=8;i++){
            calcusum+=calcnum[i];
        }
        //System.out.println(calcusum);
        int lastnum=0;
        if (calcusum%10!=0) {
            lastnum=(10-calcusum%10);
        }
        //System.out.println(lastnum);
        return lastnum;

    }
}


