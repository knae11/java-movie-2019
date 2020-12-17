package domain;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    private OrderRepository(){

    }

    public static void addOrder(int movieId, int people){
        Movie movie = MovieRepository.findMovieById(movieId);
        orders.add(new Order(movie, people));
    }

}
