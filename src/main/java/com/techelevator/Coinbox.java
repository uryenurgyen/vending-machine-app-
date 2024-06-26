package com.techelevator;

import com.techelevator.Products.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Coinbox {
    private Inventory inventory;
    private Product product = null;
    private BigDecimal userFunds = new BigDecimal(0);
    int nickle=0,dime=0,quarter=0;
    BigDecimal totalSale = new BigDecimal(0);

    public Coinbox(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addFunds(BigDecimal funds) {
        userFunds = userFunds.add(funds);
        Logger.log("FEED MONEY",funds.toString(),userFunds.toString());
    }

    public BigDecimal getUserFunds() {
        return userFunds;
    }

    public void boughtProduct(String slotID) {
        product = inventory.buyProduct(slotID);

        if (product == null) {
            System.out.println("The slot ID " + slotID + " does not exist");
            return;
        }
        if (product.getAmt() <= 0) {
            System.out.println("The Item at " + product.getSlot() + " named " + product.getName() + " is sold out");
            return;
        }
        if (product.getPrice().compareTo(userFunds) > 0) {
            System.out.println("Insufficient funds");
            return;
        }

        userFunds = userFunds.subtract(product.getPrice());
        Logger.log(product.getName() +" " + product.getSlot(), product.getPrice().toString(),userFunds.toString());
        totalSale = totalSale.add(product.getPrice());
        System.out.println("Item bought was " + product.getName() + " Cost: $" + product.getPrice().setScale(2, RoundingMode.HALF_UP) + "\nRemaining Funds: $" + getUserFunds());
        System.out.println(product.purchaseMessage());
    }

    public void returnChange() {
        BigDecimal holder = userFunds;
        while (userFunds.compareTo(BigDecimal.valueOf(0.25)) >= 0) {
            userFunds = userFunds.subtract(BigDecimal.valueOf(0.25));
            quarter++;
        }
        while (userFunds.compareTo(BigDecimal.valueOf(0.10)) >= 0) {
            userFunds = userFunds.subtract(BigDecimal.valueOf(0.10));
            dime++;
        }
        while (userFunds.compareTo(BigDecimal.valueOf(0.05)) >= 0) {
            userFunds = userFunds.subtract(BigDecimal.valueOf(0.05));
            nickle++;
        }

        Logger.log("GIVE CHANGE",holder.toString(), userFunds.toString());
        System.out.println("Your change is: $"+ holder +"\n$" + quarter + " Quarters\n$" + dime + " Dimes\n$" + nickle + " Nickles");
    }

    public void saleReport(){
        System.out.print("\n\nSales Report\n\n");
        List<String> products = inventory.productReport();
        for (String product:products){
            System.out.println(product);
        }
        System.out.println("\n\n**TOTAL SALES** $" +totalSale.setScale(2,RoundingMode.UP));

    }
}
