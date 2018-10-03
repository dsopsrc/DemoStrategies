package velox.api.layer1.simplified.demo;

import java.awt.Color;

import velox.api.layer1.annotations.Layer1ApiVersion;
import velox.api.layer1.annotations.Layer1ApiVersionValue;
import velox.api.layer1.annotations.Layer1SimpleAttachable;
import velox.api.layer1.annotations.Layer1StrategyName;
import velox.api.layer1.data.InstrumentInfo;
import velox.api.layer1.data.TradeInfo;
import velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType;
import velox.api.layer1.simplified.Api;
import velox.api.layer1.simplified.CustomModule;
import velox.api.layer1.simplified.HistoricalDataListener;
import velox.api.layer1.simplified.Indicator;
import velox.api.layer1.simplified.TradeDataListener;

@Layer1SimpleAttachable
@Layer1StrategyName("Last trade: history")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class LastTradeDemoWithHistoryData implements
    CustomModule, TradeDataListener, HistoricalDataListener {

    /** Last trade price */
    private Indicator lastTradeIndicator;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api) {
        lastTradeIndicator = api.registerIndicator("Last trade, with historical",
                GraphType.PRIMARY, Color.YELLOW);
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        lastTradeIndicator.addPoint(price);
    }
}
