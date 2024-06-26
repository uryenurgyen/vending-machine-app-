package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
	public static Scanner input = new Scanner(System.in);
	public static int userInput=0;
	public static double userMoney=0;
	public static Menus menu = new Menus();
	public static FileReader fileReader = new FileReader("vendingmachine.csv");
	public static Inventory inventory = new Inventory();
	public static Coinbox coinbox = new Coinbox(inventory);

	public static void main(String[] args) {
		inventory.addInventory(fileReader.productList());

		do {
			userInput = menu.mainMenu();
			if (userInput == 1){
				menu.spacing();
				inventory.displayAll();
				menu.pause();
			} else if (userInput == 2){
				menu.spacing();
				purchaseFunction();
			} else if (userInput == 4){
				menu.spacing();
				coinbox.saleReport();
				menu.pause();
			}else if(userInput == 3){
				System.out.println("Exiting System");
				menu.pause();
				break;
			}else {
				System.out.println("Incorrect Input");
				menu.pause();
			}

		}while(true);


	}


	public static void purchaseFunction(){


		do {
			userInput = menu.purchaseMenu(coinbox.getUserFunds());
			if (userInput == 1){
				menu.spacing();
				coinbox.addFunds(BigDecimal.valueOf(menu.feedMenu()));
			} else if (userInput == 2){
				menu.spacing();
				inventory.displayAll();
				coinbox.boughtProduct(menu.buyProduct());
				menu.pause();

			} else if (userInput == 3){
				menu.spacing();
				coinbox.returnChange();
			} else {
			System.out.println("Incorrect Input");
			menu.pause();
		}

		}while(userInput!=3);

		userInput=0;
	}


}
