package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProcessTrade {

    public List<Trade> execute(Trade trade) {
        TradeUtil tradeUtil = new TradeUtil();
        List<Trade> finalTrades = new ArrayList<>();
        List<Trade> existingTrades = tradeUtil.availableTrades();
        ValidateTrade validateTrade = new ValidateTrade();
        boolean isMaturityDateValid = validateTrade.isMaturityDateAfterTodayDate(trade);
        if (!isMaturityDateValid) {
            throw new TradeException("Maturity Date should be greater than current date");
        }
        Trade tradeToAdd = validateTrade.checkTradeVersion(trade);
        tradeToAdd.setExpired("Y");
        tradeToAdd.setCreatedDate(tradeUtil.getCurrentDate());
        existingTrades = existingTrades.stream()
                .filter(t1 -> !t1.getTradeId().equalsIgnoreCase(tradeToAdd.getTradeId()))
                .collect(Collectors.toList());
        finalTrades.addAll(existingTrades);
        finalTrades.add(tradeToAdd);
        return finalTrades;
    }

    //This method needs to be called in some interval to mark trades as expired if maturity date has gone.
    public void triggerExpireTrades() {
        TradeUtil tradeUtil = new TradeUtil();
        List<Trade> existingTrades = tradeUtil.availableTrades();
        existingTrades.stream()
                .filter(et -> TradeUtil.convertStringToDate(et.getMaturityDate()).isBefore(LocalDate.now()))
                .forEach(existingTrade -> existingTrade.setExpired("N"));
    }
}
