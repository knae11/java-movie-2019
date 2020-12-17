package controller;

import domain.Movie;
import domain.MovieRepository;
import java.time.LocalDateTime;
import java.util.List;
import utils.DateTimeUtils;
import view.InputView;
import view.OutputView;

public class MovieReservationController {
    public MovieReservationController(){

    }

    public void run(){
        List<Movie> movies = MovieRepository.getMovies();
        OutputView.printMovies(movies);
        int movieId = InputView.inputMovieId();
        OutputView.printMovieSchedule(movieId);
        LocalDateTime movieReservationTime = InputView.inputReservationTime(movieId);
        System.out.println(DateTimeUtils.format(movieReservationTime));
        // TODO 구현 진행
    }
}
