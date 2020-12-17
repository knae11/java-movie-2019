package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utils.DateTimeUtils;
import utils.ErrorCustomException;

import static utils.DateTimeUtils.createDateTime;

public class MovieRepository {
    private static List<Movie> movies = new ArrayList<>();

    static {
        Movie movie1 = new Movie(1, "생일", 8_000);
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 12:00"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 14:40"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 17:00"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 19:40"), 3));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 22:00"), 3));
        movies.add(movie1);

        Movie movie2 = new Movie(5, "돈", 10_000);
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 08:00"), 3));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 10:30"), 5));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 13:00"), 5));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 15:30"), 5));
        movies.add(movie2);

        Movie movie3 = new Movie(7, "파이브피트", 9_000);
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 13:00"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 15:40"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 18:00"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 20:40"), 3));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 23:15"), 3));
        movies.add(movie3);

        Movie movie4 = new Movie(8, "덤보", 9_000);
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 11:30"), 2));
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 16:00"), 3));
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-16 21:30"), 2));
        movies.add(movie4);
    }

    public static List<Movie> getMovies() {
        return movies;
    }

    public static boolean checkValidMovieId(int idInput) {
        return movies.stream().anyMatch(movie -> movie.getId() == idInput);
    }

    public static List<PlaySchedule> getMovieSchedule(int movieId) {
        return movies.stream().filter(movie -> movie.getId() == movieId).findFirst().get()
            .getPlaySchedules();
    }

    public static boolean checkValidTime(LocalDateTime time, int movieId){
        LocalDateTime nowTime = DateTimeUtils.createDateTime("2019-04-16 15:30");
        PlaySchedule isValidSchedule = getMovieByIdAndSchedule(time,movieId);
        boolean isScheduleAfterNow = isValidSchedule.getStartDateTime().isAfter(nowTime);
        return isScheduleAfterNow;
    }
    private static PlaySchedule getMovieByIdAndSchedule(LocalDateTime time, int movieId) {
        PlaySchedule isValidSchedule = getMovieSchedule(movieId).stream().filter(schedule -> Objects
            .equals(schedule.getStartDateTime(), time)).findFirst()
            .orElseThrow(() -> new ErrorCustomException("해당하는 스케줄이 없습니다."));
        return isValidSchedule;
    }

    public static boolean checkValidCapacity(LocalDateTime time, int movieId, int people){
        return getMovieByIdAndSchedule(time, movieId).isPossibleCapacity(people);
    }
}
