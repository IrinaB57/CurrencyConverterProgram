package CurrencyPackage;
import java.io.*;
import java.util.*;
import Currency.ICalculate;
import Currency.CoinFactory;
import Currency.Coin;
import Currency.Coins;

public class CoinSample {
    public static void main(String args[]) throws IOException {
        int screen = 1;
        Scanner input  = new Scanner(System.in);
        Scanner input2  = new Scanner(System.in);
        int choice = -1;
        double currencyValue = 0;
        String answer;
        Boolean firstTimeShown = true;
        double latestCurrency = 0;
        List<Double> currencyList = new ArrayList<Double>();
        while (true) {
            if (screen == 1) {
                if (firstTimeShown) {
                    System.out.println("Welcome to currency converter” \n" +
                            "“Please choose an option (1/2):” \n" +
                            "“1. Dollars to Shekels” \n" +
                            "“2. Shekels to Dollars” \n");
                }
                String next = input.next();
                try {
                    choice = Integer.parseInt(next);
                    if ((choice == 1) || (choice == 2)) {
                        screen++;
                        continue;
                    }
                } catch (NumberFormatException e) {
                }
                System.out.println("Invalid choice. Please reenter your choice between (1 or 2)");
                firstTimeShown = false;
            } else if (screen == 2) {
                System.out.println("Please enter an amount to convert” (amount will be in double)");
                String next = input.next();
                try {
                    currencyValue = Double.parseDouble(next);
                    if (choice == 1) {
                        Coin usd = CoinFactory.getCoinInstance(Coins.USD);
                        latestCurrency = usd.calculate(currencyValue);
                    } else {
                        Coin ils = CoinFactory.getCoinInstance(Coins.ILS);
                        latestCurrency = ils.calculate(currencyValue);
                    }
                    currencyList.add(latestCurrency);
                    screen++;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice...");
                }

            } else if (screen == 3) {
                System.out.println("Result: " + latestCurrency);
                System.out.println("Do you wanna start again. (Y/N)");
                firstTimeShown = true;
                answer = input2.nextLine();
                if (answer.toLowerCase().startsWith("n")) {
                    break;
                } else {
                    screen = 1;
                }
            }
        }
        System.out.println("Thanks for using our currency converter");
        System.out.println("Here is our result list: ");
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("c:\\A1\\results.txt"));
        for (int counter = 0; counter < currencyList.size(); counter++) {
            System.out.println(currencyList.get(counter));
            outputWriter.write(Double.toString(currencyList.get(counter)));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();

    }
}
