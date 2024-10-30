import pandas as pd
import backtrader as bt
import zstandard as zstd
import io

# 1. Load and decompress the Databento .zst file
def load_databento_data(file_path):
    with open(file_path, 'rb') as f_in:
        decompressor = zstd.ZstdDecompressor()
        data = decompressor.decompress(f_in.read())
        df = pd.read_csv(io.StringIO(data.decode('utf-8')))
    return df

# 2. Prepare data for Backtrader
def prepare_data_for_backtrader(df):
    df['datetime'] = pd.to_datetime(df['ts_event'])
    df.set_index('datetime', inplace=True)

    df = df[['open', 'high', 'low', 'close', 'volume']]
    df.columns = ['Open', 'High', 'Low', 'Close', 'Volume']

    return df

# 3. Define the Turtle Strategy
class TurtleStrategy(bt.Strategy):
    params = (
        ("enter_period", 34),
        ("exit_period", 20),
        ("atr_period", 22),
        ("atr_multiplier", 2.0),
    )

    def __init__(self):
        # Indicators
        self.atr = bt.indicators.ATR(period=self.params.atr_period)
        self.highest = bt.indicators.Highest(self.data.high, period=self.params.enter_period)
        self.lowest = bt.indicators.Lowest(self.data.low, period=self.params.enter_period)

        self.entry_price = None  # Store entry price to calculate stops

    def next(self):
        # Ensure enough data is available before applying logic
        if len(self.data) < max(self.params.enter_period, self.params.atr_period):
            return  # Skip until we have enough data

        if not self.position:  # No open position
            if self.data.close[0] > self.highest[-1]:  # Breakout above highest high
                self.entry_price = self.data.close[0]
                self.buy()  # Enter long position
            elif self.data.close[0] < self.lowest[-1]:  # Breakout below lowest low
                self.entry_price = self.data.close[0]
                self.sell()  # Enter short position

        else:  # Already in a position
            if self.position.size > 0:  # Long position
                if self.data.close[0] < self.entry_price - self.atr[0] * self.params.atr_multiplier:
                    self.close()  # Exit long position

            elif self.position.size < 0:  # Short position
                if self.data.close[0] > self.entry_price + self.atr[0] * self.params.atr_multiplier:
                    self.close()  # Exit short position

# 4. Set up and run the backtest
def run_backtest(data):
    cerebro = bt.Cerebro()
    cerebro.addstrategy(TurtleStrategy)

    feed = bt.feeds.PandasData(dataname=data)
    cerebro.adddata(feed)

    cerebro.broker.setcash(3000)

    print("Starting Portfolio Value: %.2f" % cerebro.broker.getvalue())
    cerebro.run()
    print("Final Portfolio Value: %.2f" % cerebro.broker.getvalue())

    cerebro.plot()

# 5. Main script execution
if __name__ == "__main__":
    df = load_databento_data('mnq_1hr.csv.zst')
    data = prepare_data_for_backtrader(df)
    run_backtest(data)
