package controller;

import domain.Movie;
import domain.MovieRepository;
import java.util.List;
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
        // TODO 구현 진행
    }
}
