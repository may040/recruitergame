package de.kit.recruitergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTrain {


    public static void main(String[] args) {
        List<String> names = List.of("hans", "max", "dennis", "max");

        Map<Boolean, List<String>> collect = names.stream().collect(Collectors.groupingBy(p->p.contains("a")));
        //collect.forEach((i,l)->System.out.println(i +" "+ l));

        List<Order> orders = List.of(
                new Order("Anna", "Laptop", 1),
                new Order("Bob", "Mouse", 2),
                new Order("Anna", "Keyboard", 1),
                new Order("Chris", "Laptop", 2),
                new Order("Bob", "Monitor", 1)
        );

        Map<String, List<Order>> customersOrders = orders.stream().collect(Collectors.groupingBy(ord -> ord.customer));
        //customersOrders.forEach((i,l)->System.out.println(i +" "+ l));
        Map<String, Long> customerCounts = orders.stream().collect(Collectors.groupingBy(order -> order.customer, Collectors.counting()));
        //customerCounts.forEach((i,l)->System.out.println(i +" "+ l));
        Map<String, Integer> productCounts = orders.stream().collect(Collectors.groupingBy(order -> order.product, Collectors.summingInt(o-> o.quantity)));
        //productCounts.forEach((i,l)->System.out.println(i +" "+ l));







    }

    static class Order {
        String customer;
        String product;
        int quantity;

        Order(String customer, String product, int quantity) {
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "customer='" + customer + '\'' +
                    ", product='" + product + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
