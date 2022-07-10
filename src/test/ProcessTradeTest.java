package test;

import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProcessTradeTest {

    TradeUtil tradeUtil = new TradeUtil();
    List<Trade> existingTrades = null;

    @Before
    public void setup() {
        existingTrades = tradeUtil.availableTrades();
    }

    @Test
    public void shouldProcessTrade() {
        Trade mockTrade = new Trade(
                "T4", 1, "CP-1",
                "B1", "20-05-2099", tradeUtil.getCurrentDate(),"Y");
        ProcessTrade processTrade = new ProcessTrade();
        List<Trade> trades = processTrade.execute(mockTrade);
        Assert.assertEquals(trades.isEmpty(), false);
        Assert.assertEquals(trades.size(), 4);
        Assert.assertEquals(trades.get(3).getTradeId(), "T4");
    }

    @Test(expected = TradeException.class)
    public void shouldNotProcessTradeWhenLowerVersionExist() {
        Trade mockTrade = new Trade(
                "T2", 1, "CP-1",
                "B1", "20-05-2099", tradeUtil.getCurrentDate(),"Y");
        ProcessTrade processTrade = new ProcessTrade();
        List<Trade> trades = processTrade.execute(mockTrade);
    }

    @Test(expected = TradeException.class)
    public void shouldNotProcessTradeWhenMaturityDateisLower() {
        Trade mockTrade = new Trade(
                "T5", 1, "CP-1",
                "B1", "20-05-2021", tradeUtil.getCurrentDate(),"Y");
        ProcessTrade processTrade = new ProcessTrade();
        List<Trade> trades = processTrade.execute(mockTrade);
    }
}
