/**
 * Copyright (C) 2012 - 2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.xchange.examples.kraken.trading;

import java.io.IOException;
import java.math.BigDecimal;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.examples.kraken.KrakenExampleUtils;
import com.xeiam.xchange.kraken.dto.trade.KrakenOrderResponse;
import com.xeiam.xchange.kraken.service.polling.KrakenTradeServiceRaw;
import com.xeiam.xchange.service.polling.PollingTradeService;

/**
 * Test placing a limit order at Kraken
 */
public class KrakenLimitOrderDemo {

  public static void main(String[] args) throws IOException {

    Exchange krakenExchange = KrakenExampleUtils.createTestExchange();

    generic(krakenExchange);
    raw(krakenExchange);
  }

  private static void generic(Exchange krakenExchange) throws IOException {

    PollingTradeService tradeService = krakenExchange.getPollingTradeService();

    OrderType orderType = (OrderType.ASK);
    BigDecimal tradeableAmount = new BigDecimal("0.01");
    BigDecimal price = new BigDecimal("65.25");

    LimitOrder limitOrder = new LimitOrder(orderType, tradeableAmount, CurrencyPair.BTC_LTC, "", null, price);

    String orderID = tradeService.placeLimitOrder(limitOrder);
    System.out.println("Limit Order ID: " + orderID);
  }

  private static void raw(Exchange krakenExchange) throws IOException {

    KrakenTradeServiceRaw tradeService = (KrakenTradeServiceRaw) krakenExchange.getPollingTradeService();

    OrderType orderType = (OrderType.ASK);
    BigDecimal tradeableAmount = new BigDecimal("0.01");
    BigDecimal price = new BigDecimal("65.25");

    LimitOrder limitOrder = new LimitOrder(orderType, tradeableAmount, CurrencyPair.BTC_LTC, "", null, price);

    KrakenOrderResponse orderResponse = tradeService.placeKrakenLimitOrder(limitOrder);
    System.out.println("Limit Order response: " + orderResponse);
  }
}
