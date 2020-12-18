package controller;

import domain.Movie;
import domain.MovieRepository;
import domain.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import utils.DateTimeUtils;
import view.InputView;
import view.OutputView;

public class MovieReservationController {
    public MovieReservationController(){

    }

    public void run(){
        while(true){
            chooseMovie();
            String moreOrNot = InputView.inputMoreReservationOrPay();
            if(moreOrNot.equals("1")){
                break;
            }
        }
        payMovie();
    }
    //TODO : 결제기능
    private void payMovie() {
       int point =  InputView.inputPoint();
       String payment = InputView.inputPayment();
       double totalToPay = OrderRepository.calculateTotalPay(point, payment);
       OutputView.printTotalToPay(totalToPay);
    }

    private void chooseMovie() {
        List<Movie> movies = MovieRepository.getMovies();
        OutputView.printMovies(movies);
        int movieId = InputView.inputMovieId();
        OutputView.printMovieSchedule(movieId);
        LocalDateTime movieReservationTime = InputView.inputReservationTime(movieId);
        int people = InputView.inputHowMany(movieId, movieReservationTime);
        OrderRepository.addOrder(movieId, people);
    }
}
