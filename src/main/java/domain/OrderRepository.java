package domain;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    private OrderRepository() {

    }

    public static void addOrder(int movieId, int people) {
        Movie movie = MovieRepository.findMovieById(movieId);
        orders.add(new Order(movie, people));
    }

    public static boolean overTotalPrice(int point) {
        return orders.stream().mapToInt(Order::getPrice).sum() >= point;
    }

    public static double calculateTotalPay(int point, String payment) {
        int total = orders.stream().mapToInt(Order::getPrice).sum();
        total -= point;
        if (payment.equals("1")) {
            return total *= 0.95;
        }
        if (payment.equals("2")) {
            return total *= 0.98;
        }
        return total;
    }
}
