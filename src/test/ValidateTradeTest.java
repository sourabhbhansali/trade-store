package test;

import main.Trade;
import main.TradeException;
import main.TradeUtil;
import main.ValidateTrade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidateTradeTest {

    TradeUtil tradeUtil = new TradeUtil();
    List<Trade> existingTrades = null;

    @Before
    public void setup() {
        existingTrades = tradeUtil.availableTrades();
    }

    @Test
    public void shouldReturnFalseWhenMaturityDateIsLessThanCurrentDate() {
        Trade mockTrade = new Trade(
                "T1", 1, "CP-1",
                "B1", "20-05-2020", tradeUtil.getCurrentDate(),"N");
        ValidateTrade validateTrade = new ValidateTrade();
        boolean isValid = validateTrade.isMaturityDateAfterTodayDate(mockTrade);
        Assert.assertEquals(isValid, false);
    }

    @Test
    public void shouldReturnTrueWhenMaturityDateIsLessThanCurrentDate() {
        Trade mockTrade = new Trade(
                "T1", 1, "CP-1",
                "B1", "20-05-2099", tradeUtil.getCurrentDate(),"N");
        ValidateTrade validateTrade = new ValidateTrade();
        boolean isValid = validateTrade.isMaturityDateAfterTodayDate(mockTrade);
        Assert.assertEquals(isValid, true);
    }

    @Test(expected = TradeException.class)
    public void shouldRejectTradeAndThrowExceptionWhenTradeVersionIsLess() {
        Trade mockTrade = new Trade(
                "T2", 1, "CP-2",
                "B1", "20-05-2021", tradeUtil.getCurrentDate(), "N");
        ValidateTrade validateTrade = new ValidateTrade();
        validateTrade.checkTradeVersion(mockTrade);
    }

    @Test
    public void shouldProcessTradeWhenVersionIsSameOrGreater() {
        Trade mockTrade = new Trade(
                "T2", 2, "CP-3",
                "B3", "20-05-2021", tradeUtil.getCurrentDate(), "N");
        ValidateTrade validateTrade = new ValidateTrade();
        Trade trade = validateTrade.checkTradeVersion(mockTrade);
        Assert.assertEquals(trade.getCounterPartyId(), "CP-3");
        Assert.assertEquals(trade.getBookId(), "B3");
    }
}
