package ua.opnu;

import ua.opnu.java.inheritance.account.BankingAccount;
import ua.opnu.java.inheritance.account.Credit;
import ua.opnu.java.inheritance.account.Debit;
import ua.opnu.java.inheritance.account.Startup;
import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;
import ua.opnu.java.inheritance.point.Point;

public class Main {
    public static void main(String[] args) {
        Employee clerk = new Employee("Oksana");
        DiscountBill bill = new DiscountBill(clerk, true);

        bill.add(new Item("milk", 20.0, 2.0));
        bill.add(new Item("bread", 15.0, 1.5));

        System.out.println("Total: " + bill.getTotal());
        System.out.println("Discount count: " + bill.getDiscountCount());
        System.out.println("Discount amount: " + bill.getDiscountAmount());
        System.out.println("Discount percent: " + bill.getDiscountPercent());

        Startup startup = new Startup(10000);
        MinMaxAccount account = new MinMaxAccount(startup);
        account.debit(new Debit(50000));
        account.credit(new Credit(-20000));
        account.debit(new Debit(10000));
        System.out.println("Поточний баланс: " + account.toString());
        System.out.println("Мінімальний баланс: " + formatBalance(account.getMin()));
        System.out.println("Максимальний баланс: " + formatBalance(account.getMax()));

        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(4, 6, 8);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("Distance p1 -> p2: " + p1.distance(p2));
        System.out.println("Distance from origin (p1): " + p1.distanceFromOrigin());

        p1.setLocation(7, 8); // z має скинутися до 0
        System.out.println("After p1.setLocation(7, 8): " + p1);

        p1.setLocation(1, 2, 3); // назад
        System.out.println("After p1.setLocation(1, 2, 3): " + p1);

        Employee employee = new Employee("Clerk Ivan");

        DiscountBill2 bill2 = new DiscountBill2(employee, true);

        bill2.add(new Item("Candy Bar", 1.35, 0.25));
        bill2.add(new Item("Toy Car", 3.25, 0.50));
        bill2.add(new Item("Apple", 0.30, 0.05));
        bill2.add(new Item("Newspaper", 0.99, 0.0));

        System.out.println("Clerk: " + bill2.getClerk().getName());
        System.out.println("Total: $" + bill2.getTotal());
        System.out.println("Discount count: " + bill2.getDiscountCount());
        System.out.println("Discount amount: $" + bill2.getDiscountAmount());
        System.out.println("Discount percent: " + bill2.getDiscountPercent() + "%");
    }

    private static String formatBalance(int balanceInCents) {
        boolean isNegative = balanceInCents < 0;
        int abs = Math.abs(balanceInCents);
        int hryvnia = abs / 100;
        int kopiyky = abs % 100;
        return (isNegative ? "-" : "") + hryvnia + "." + (kopiyky < 10 ? "0" : "") + kopiyky + " грн";
    }
}

class DiscountBill extends GroceryBill {
    private final boolean regularCustomer;
    private int discountCount;
    private double discountAmount;
    private double totalBeforeDiscount;
    private double finalTotal;

    public DiscountBill(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.totalBeforeDiscount = 0.0;
        this.finalTotal = 0.0;
    }

    @Override
    public void add(Item item) {
        double price = item.getPrice();
        double discount = item.getDiscount();
        totalBeforeDiscount += price;

        if (regularCustomer && discount > 0.0) {
            discountCount++;
            discountAmount += discount;
            finalTotal += price - discount;
        } else {
            finalTotal += price;
        }
    }

    @Override
    public double getTotal() {
        return Math.rint(finalTotal * (double) 100.0F) / (double) 100.0F;
    }

    public int getDiscountCount() {
        return regularCustomer ? discountCount : 0;
    }

    public double getDiscountAmount() {
        return regularCustomer ? discountAmount : 0.0;
    }

    public double getDiscountPercent() {
        if (!regularCustomer || totalBeforeDiscount == 0.0) {
            return 0.0;
        }
        double discountedTotal = totalBeforeDiscount - discountAmount;
        return 100.0 - (discountedTotal * 100.0 / totalBeforeDiscount);
    }
}

class MinMaxAccount extends BankingAccount {
    private int min;
    private int max;

    public MinMaxAccount(Startup s) {
        super(s);
        int initialBalance = s.getBalance();
        this.min = initialBalance;
        this.max = initialBalance;
    }

    @Override
    public void debit(Debit d) {
        super.debit(d);
        updateMinMax();
    }

    @Override
    public void credit(Credit c) {
        super.credit(c);
        updateMinMax();
    }

    private void updateMinMax() {
        int currentBalance = getBalance();
        if (currentBalance < min) {
            min = currentBalance;
        }
        if (currentBalance > max) {
            max = currentBalance;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

class Point3D extends Point {
    private int z;

    public Point3D() {
        super(0, 0);
        this.z = 0;
    }

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public void setLocation(int x, int y, int z) {
        super.setLocation(x, y);
        this.z = z;
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.z = 0;
    }

    public int getZ() {
        return this.z;
    }

    public double distance(Point3D p) {
        int dx = this.getX() - p.getX();
        int dy = this.getY() - p.getY();
        int dz = this.z - p.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(getX() * getX() + getY() * getY() + z * z);
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + z + ")";
    }
}

class DiscountBill2 extends GroceryBill {
    private final boolean regularCustomer;
    private int discountCount;
    private double discountAmount;
    private double totalBeforeDiscount;
    private double finalTotal;

    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.totalBeforeDiscount = 0.0;
        this.finalTotal = 0.0;
    }

    @Override
    public void add(Item item) {
        double price = item.getPrice();
        double discount = item.getDiscount();
        totalBeforeDiscount += price;

        if (regularCustomer && discount > 0.0) {
            discountCount++;
            discountAmount += discount;
            finalTotal += price - discount;
        } else {
            finalTotal += price;
        }
    }

    @Override
    public double getTotal() {
        return Math.rint(finalTotal * 100.0) / 100.0;
    }

    public int getDiscountCount() {
        return regularCustomer ? discountCount : 0;
    }

    public double getDiscountAmount() {
        return regularCustomer ? discountAmount : 0.0;
    }

    public double getDiscountPercent() {
        if (!regularCustomer || totalBeforeDiscount == 0.0) {
            return 0.0;
        }
        double discountedTotal = totalBeforeDiscount - discountAmount;
        return 100.0 - (discountedTotal * 100.0 / totalBeforeDiscount);
    }
}


