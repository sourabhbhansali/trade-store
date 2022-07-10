package main;

import java.util.List;
import java.util.Scanner;

public class TradeStore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter main.Trade Id - ");
        String tradeId = sc.nextLine();
        System.out.print("Enter Version - ");
        int version = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Counter Party Id- ");
        String counterPartyId = sc.nextLine();
        System.out.print("Enter Book Id- ");
        String bookId = sc.nextLine();
        System.out.print("Enter Maturity Date in (dd-MM-yyyy) format- ");
        String maturityDate = sc.nextLine();
        ProcessTrade processTrade = new ProcessTrade();
        Trade trade = new Trade(tradeId, version, counterPartyId, bookId, maturityDate);
        List<Trade> trades =  processTrade.execute(trade);
        if (trades != null)
            trades.stream().forEach(System.out::println);
        processTrade.triggerExpireTrades();
    }


}
