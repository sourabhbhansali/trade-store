package main;

import java.time.LocalDate;
import java.util.List;

public class ValidateTrade {

    public Trade checkTradeVersion(Trade trade) {
        Trade matchedTrade = checkIfTradeAlreadyExist(trade);
        if (matchedTrade != null) {
            if (trade.getVersion() < matchedTrade.getVersion()) {
                throw new TradeException("Trade cannot proceed, as version is less than existing trade version");
            } else if(trade.getVersion() == matchedTrade.getVersion()
                        || trade.getVersion() > matchedTrade.getVersion()) {
                return trade;
            }
        }
        return trade;
    }

    public boolean isMaturityDateAfterTodayDate(Trade trade) {
        if (trade != null) {
            LocalDate maturityDate = TradeUtil.convertStringToDate(trade.getMaturityDate());
            return maturityDate.isAfter(LocalDate.now());
        }
        return false;
    }

    private Trade checkIfTradeAlreadyExist(Trade trade) {
        List<Trade> existingTrades = getAvailableTrades();
        Trade matchedTrade = existingTrades.stream()
                .filter(existingTrade -> existingTrade.getTradeId()
                        .equalsIgnoreCase(trade.getTradeId()))
                .findAny().orElse(null);
        return matchedTrade;
    }

    private List<Trade> getAvailableTrades() {
        TradeUtil tradeUtil = new TradeUtil();
        List<Trade> existingTrades = tradeUtil.availableTrades();
        return existingTrades;
    }


}
