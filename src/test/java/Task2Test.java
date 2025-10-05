import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import ua.opnu.MinMaxAccount;
import ua.opnu.java.inheritance.account.Credit;
import ua.opnu.java.inheritance.account.Debit;
import ua.opnu.java.inheritance.account.Startup;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Nested
    @DisplayName("Tests for the second task")
    class Test {

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 1")
        void test1() {
            MinMaxAccount account = new MinMaxAccount(new Startup(150));

            int expected = 150;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 2")
        void test2() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));

            int expected = 0;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 3")
        void test3() {
            MinMaxAccount account = new MinMaxAccount(new Startup(-300));

            int expected = -300;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 1")
        void test4() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));

            int expected = 150;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 2")
        void test5() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));
            account.debit(new Debit(300));

            int expected = 450;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 3")
        void test6() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(450));
            account.credit(new Credit(-225));

            int expected = 225;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 4")
        void test7() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));
            account.credit(new Credit(-25));
            account.debit(new Debit(75));

            int expected = 200;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 5")
        void test8() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(75));
            account.debit(new Debit(100));
            account.credit(new Credit(-25));

            int expected = 150;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 6")
        void test9() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));
            account.credit(new Credit(-75));
            account.debit(new Debit(450));
            account.credit(new Credit(-175));

            int expected = 350;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getBalance() 7")
        void test10() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.credit(new Credit(-150));
            account.debit(new Debit(25));
            account.debit(new Debit(75));
            account.credit(new Credit(-300));

            int expected = -350;
            assertEquals(expected, account.getBalance());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 1")
        void test11() {
            MinMaxAccount account = new MinMaxAccount(new Startup(75));

            int expected = 75;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 2")
        void test12() {
            MinMaxAccount account = new MinMaxAccount(new Startup(300));
            account.credit(new Credit(-150));
            account.debit(new Debit(200));
            account.debit(new Debit(25));
            account.debit(new Debit(250));
            account.credit(new Credit(-150));

            int expected = 150;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 3")
        void test13() {
            MinMaxAccount account = new MinMaxAccount(new Startup(-300));
            account.debit(new Debit(150));
            account.debit(new Debit(25));
            account.debit(new Debit(125));
            account.debit(new Debit(300));

            int expected = -300;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 4")
        void test14() {
            MinMaxAccount account = new MinMaxAccount(new Startup(150));
            account.debit(new Debit(25));
            account.debit(new Debit(25));
            account.credit(new Credit(-175));
            account.debit(new Debit(600));

            int expected = 25;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 5")
        void test15() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(25));
            account.debit(new Debit(150));
            account.credit(new Credit(-25));
            account.debit(new Debit(300));

            int expected = 0;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMin() 6")
        void test16() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));
            account.credit(new Credit(-25));
            account.credit(new Credit(-250));
            account.debit(new Debit(300));
            account.credit(new Credit(-150));
            account.debit(new Debit(400));

            int expected = -125;
            assertEquals(expected, account.getMin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 1")
        void test17() {
            MinMaxAccount account = new MinMaxAccount(new Startup(75));

            int expected = 75;
            assertEquals(expected, account.getMax());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 2")
        void test18() {
            MinMaxAccount account = new MinMaxAccount(new Startup(300));
            account.credit(new Credit(-150));
            account.debit(new Debit(200));
            account.debit(new Debit(25));
            account.debit(new Debit(250));
            account.credit(new Credit(-150));

            int expected = 625;
            assertEquals(expected, account.getMax());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 3")
        void test19() {
            MinMaxAccount account = new MinMaxAccount(new Startup(-300));
            account.debit(new Debit(150));
            account.debit(new Debit(25));
            account.debit(new Debit(125));
            account.debit(new Debit(300));

            int expected = 300;
            assertEquals(expected, account.getMax());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 4")
        void test20() {
            MinMaxAccount account = new MinMaxAccount(new Startup(150));
            account.debit(new Debit(25));
            account.debit(new Debit(25));
            account.credit(new Credit(-175));
            account.debit(new Debit(600));

            int expected = 625;
            assertEquals(expected, account.getMax());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 5")
        void test21() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(25));
            account.debit(new Debit(150));
            account.debit(new Debit(300));
            account.credit(new Credit(-25));

            int expected = 475;
            assertEquals(expected, account.getMax());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getMax() 6")
        void test22() {
            MinMaxAccount account = new MinMaxAccount(new Startup(0));
            account.debit(new Debit(150));
            account.debit(new Debit(300));
            account.credit(new Credit(-150));
            account.debit(new Debit(400));
            account.credit(new Credit(-25));
            account.credit(new Credit(-250));

            int expected = 700;
            assertEquals(expected, account.getMax());
        }
    }
}
