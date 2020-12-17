package domain;


public class Order {
    private final int people;
    private final Movie movie;

    public Order(Movie movie, int people){
        this.movie = movie;
        this.people = people;
    }

    public int getPrice(){
        return movie.getPrice()*people;
    }
}
