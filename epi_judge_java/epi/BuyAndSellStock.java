package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        double maxProfit = 0.0d;
        double minPrice = Double.MAX_VALUE;

        for (Double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    /**
     * This is O(n^2)
     *
     * @param args
     */
  /*  public static double computeMaxProfitBruteForce(List<Double> prices) {
        double maxProfit = 0.0d;
        for (int i = 0; i < prices.size(); i++) {
            for (int j = i + 1; j < prices.size(); j++) {
                if (prices.get(j) - prices.get(i) > maxProfit) {
                    maxProfit = prices.get(j) - prices.get(i);
                }
            }
        }


        return maxProfit;
    }*/
    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
