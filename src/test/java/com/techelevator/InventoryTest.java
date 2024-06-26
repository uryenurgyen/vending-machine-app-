package com.techelevator;

import com.techelevator.Products.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testAddInventory() {
        inventory.addInventory(Arrays.asList(
                "A1|Chips|1.50|Chip",
                "B1|Candy|1.00|Candy",
                "C1|Drink|1.25|Drink",
                "D1|Gum|0.75|Gum"
        ));

        Product productA1 = inventory.buyProduct("A1");
        Product productB1 = inventory.buyProduct("B1");
        Product productC1 = inventory.buyProduct("C1");
        Product productD1 = inventory.buyProduct("D1");

        assertNotNull(productA1);
        assertNotNull(productB1);
        assertNotNull(productC1);
        assertNotNull(productD1);

        assertEquals("Chips", productA1.getName());
        assertEquals("Candy", productB1.getName());
        assertEquals("Drink", productC1.getName());
        assertEquals("Gum", productD1.getName());

        assertEquals(5, productA1.getAmt());
        assertEquals(5, productB1.getAmt());
        assertEquals(5, productC1.getAmt());
        assertEquals(5, productD1.getAmt());
    }

    @Test
    public void testDisplayAll() {
        inventory.addInventory(Arrays.asList(
                "A1|Chips|1.50|Chip",
                "B1|Candy|1.00|Candy",
                "C1|Drink|1.25|Drink",
                "D1|Gum|0.75|Gum"
        ));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        inventory.displayAll();

        String actualOutput = outContent.toString().trim();

        String expectedOutput =
                "****************************************************************************\n" +
                        "                       Available Product for Sale\n" +
                        "****************************************************************************\n" +
                        "Slot         Item Name             Price         Type         Stock\n"+
                        "A1           Chips                 $1.50         Chip         5\n"+
                        "B1           Candy                 $1.00         Candy        5\n"+
                        "C1           Drink                 $1.25         Drink        5\n"+
                        "D1           Gum                   $0.75         Gum          5";

        System.setOut(System.out);  // Reset System.out before printing

        System.out.println("Expected output:");
        System.out.println(expectedOutput);
        System.out.println("\nActual output:");
        System.out.println(actualOutput);

        System.out.println("\nDetailed comparison:");
        String[] expectedLines = expectedOutput.split("\n");
        String[] actualLines = actualOutput.split("\n");

        for (int i = 0; i < Math.max(expectedLines.length, actualLines.length); i++) {
            String expectedLine = i < expectedLines.length ? expectedLines[i] : "";
            String actualLine = i < actualLines.length ? actualLines[i] : "";

            if (!expectedLine.equals(actualLine)) {
                System.out.println("Line " + (i + 1) + " differs:");
                System.out.println("Expected: \"" + expectedLine + "\"");
                System.out.println("Actual:   \"" + actualLine + "\"");
                System.out.println("Difference:");
                for (int j = 0; j < Math.max(expectedLine.length(), actualLine.length()); j++) {
                    char expectedChar = j < expectedLine.length() ? expectedLine.charAt(j) : ' ';
                    char actualChar = j < actualLine.length() ? actualLine.charAt(j) : ' ';
                    if (expectedChar != actualChar) {
                        System.out.println("  Position " + (j + 1) + ": Expected '" + expectedChar + "', Actual '" + actualChar + "'");
                    }
                }
            }
        }

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
    @Test
    public void testBuyProduct() {
        inventory.addInventory(Arrays.asList(
                "A1|Chips|1.50|Chip",
                "B1|Candy|1.00|Candy"
        ));

        Product product = inventory.buyProduct("A1");
        assertNotNull(product);
        assertEquals("Chips", product.getName());

        Product nonExistentProduct = inventory.buyProduct("Z9");
        assertNull(nonExistentProduct);
    }
}
