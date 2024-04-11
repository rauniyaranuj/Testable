import java.util.ArrayList;

import java.util.List;
import java.time.LocalDateTime;
class Item {
    private int upc;
    private String name;
    private double price;
    private double taxRate;

    public Item(int upc, String name, double price, double taxRate) {
        this.upc = upc;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
    }
    
    public double getPrice() {
        return price;
    }

    public String toString() {
        return upc + " " + name + " " + price + " " + taxRate;
    }

	public double getTaxRate() {
		return taxRate;
	}

	public int getUpc() {
		return upc;
	}

	public String getName() {
		return name;
	}
}

class Cashier {
    private String name;

    public Cashier(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class Register {
    private int id;

    public Register(int id) {
        this.id = id;
    }

    public String toString() {
        return String.valueOf(id);
    }
}

class SaleLineItem {
    private Item item;
    private int quantity;

    public SaleLineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }


    public double getTax() {
        return item.getPrice() * item.getTaxRate() * quantity;
    }
    


    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public String toString() {
        return item.getUpc() + " " + item.getName() + " " + quantity + " " + getSubtotal() + " " + String.format("%.2f", getTax());
    }
}

class Sale {
    private Register register;
    private Cashier cashier;
    private List<SaleLineItem> items;

    public Sale(Register register, Cashier cashier) {
        this.register = register;
        this.cashier = cashier;
        this.items = new ArrayList<>();
    }

    public void addSaleLineItem(SaleLineItem item) {
        items.add(item);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (SaleLineItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double getTax() {
        double tax = 0;
        for (SaleLineItem item : items) {
            tax += item.getTax();
        }
        return tax;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime currentDateTime = LocalDateTime.now();
        sb.append("Session: Cashier: ").append(cashier).append(" Register: ").append(register).append(" Total: ").append(String.format("%.2f", getTotal())).append("\n");
        sb.append("  Sale: Subtotal = ").append(String.format("%.2f",getSubtotal())).append(" Tax = ").append(String.format("%.2f", getTax())).append(" Total = ").append(String.format("%.2f",getTotal())).append("\n");
        for (SaleLineItem item : items) {
//            sb.append("  Sale: Subtotal = ").append(String.format("%.2f", item.getSubtotal())).append(" Tax = ").append(String.format("%.2f", item.getTax())).append(" Total = ").append(String.format("%.2f", item.getTotal())).append("\n");
            sb.append("     ").append(item).append("  ").append(currentDateTime).append("\n");
        }
        return sb.toString();
    }

}

public class StoreTest {
    public static void main(String[] args) {
        // Create Items
    	

        Item item1 = new Item(1001, "Turkey Sandwich", 2.29, 0.07);
        Item item2 = new Item(1002, "Ham Sandwich", 2.59, 0.07);
        Item item3 = new Item(1003, "Coke", 0.97, 0);
        Item item4 = new Item(1004, "Dr. Pepper", 0.79, 0);

        // Create Cashiers
        Cashier cashier1 = new Cashier("David");
        Cashier cashier2 = new Cashier("Sally");

        // Create Registers
        Register register1 = new Register(1);
        Register register2 = new Register(2);

        // Create Sale
        Sale sale = new Sale(register2, cashier1);
        sale.addSaleLineItem(new SaleLineItem(item3, 1));
        sale.addSaleLineItem(new SaleLineItem(item2, 2));

        // Display Data
        System.out.println("==============");
        System.out.println("Cashiers");
        System.out.println("==============");
        System.out.println(cashier1);
        System.out.println(cashier2);
        System.out.println("==============");
        System.out.println("Registers");
        System.out.println("==============");
        System.out.println(register1);
        System.out.println(register2);
        System.out.println("==============");
        System.out.println("Items");
        System.out.println("==============");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);
        System.out.println("==============");
        System.out.println("Sessions");
        System.out.println("==============");
        System.out.println(sale);
    }
}
