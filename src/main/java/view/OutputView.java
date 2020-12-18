package view;

import domain.Movie;

import domain.MovieRepository;
import java.util.List;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
    
    public static void printMovieSchedule(int movieId){
        System.out.println("## 선택하신 영화의 시간표 입니다.");
        MovieRepository.getMovieSchedule(movieId).stream().forEach(System.out::print);
    }


    public static void printTotalToPay(double totalToPay) {
        System.out.println("## 계산할 총 금액은 "+ totalToPay + "원 입니다.");
    }
}
