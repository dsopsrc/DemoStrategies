package velox.api.layer1.simplified.demo;

import velox.api.layer1.annotations.Layer1ApiVersion;
import velox.api.layer1.annotations.Layer1ApiVersionValue;
import velox.api.layer1.annotations.Layer1SimpleAttachable;
import velox.api.layer1.annotations.Layer1StrategyName;
import velox.api.layer1.data.InstrumentInfo;
import velox.api.layer1.data.TradeInfo;
import velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator;
import velox.api.layer1.simplified.*;

import java.awt.*;

@Layer1SimpleAttachable
@Layer1StrategyName("EMA")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION2)
public class Ema implements CustomModule, TradeDataListener, IntervalListener {
    private Indicator emaIndicator;
    private double lastPrice = Double.NaN;
    private double emaValue = Double.NaN;
    private double alpha = 0.99;

    @Override
    public void initialize(String s, InstrumentInfo instrumentInfo, Api api, InitialState initialState) {
        emaIndicator = api.registerIndicator("EMA", Layer1ApiUserMessageModifyIndicator.GraphType.PRIMARY);
        emaIndicator.setColor(Color.GREEN);
    }

    @Override
    public void stop() {

    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        lastPrice = price;
    }

    @Override
    public long getInterval() {
        return Intervals.INTERVAL_1_SECOND;
    }

    @Override
    public void onInterval() {
        if (Double.isNaN(lastPrice)) {
            return;
        }
        // Update EMA
        if (Double.isNaN(emaValue)) {
            emaValue = lastPrice;
        } else {
            emaValue = emaValue * alpha + lastPrice * (1.0 - alpha);
        }
        // Draw on screen.
        emaIndicator.addPoint(emaValue);
    }
}
