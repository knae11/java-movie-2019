package view;

import domain.MovieRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import utils.DateTimeUtils;
import utils.ErrorCustomException;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        while (true) {
            try {
                System.out.println("## 예약할 영화를 선택하세요.");
                String idInput = scanner.nextLine().trim();
                return checkIdNumber(idInput);
            } catch (ErrorCustomException errorCustomException) {
                System.out.println(errorCustomException.getMessage());
            }
        }
    }

    private static int checkIdNumber(String idInput) {
        try {
            int id = Integer.parseInt(idInput);
            checkValidMovieId(id);
            return id;
        } catch (NumberFormatException n) {
            throw new ErrorCustomException("숫자를 입력해 주세요.");
        }
    }

    private static void checkValidMovieId(int id) {
        if (!MovieRepository.checkValidMovieId(id)) {
            throw new ErrorCustomException("예매 가능한 영화가 없습니다.");
        }
    }

    public static LocalDateTime inputReservationTime(int movieId) {
        while (true) {
            try {
                System.out.println("## 예약할 시간를 선택하세요. 정확하게 같은 형식으로 입력");
                String timeInput = scanner.nextLine().trim();
                return checkTime(timeInput, movieId);
            } catch (ErrorCustomException errorCustomException) {
                System.out.println(errorCustomException.getMessage());
            }
        }
    }

    private static LocalDateTime checkTime(String timeInput, int movieId) {
        try {
            LocalDateTime time = DateTimeUtils.createDateTime(timeInput);
            checkExistMovieSchedule(time, movieId);
            checkIsOneHourWithin(time);
            return time;
        } catch (DateTimeParseException d) {
            throw new ErrorCustomException("yyyy-MM-dd HH:mm 같은 형식으로 입력해 주세요.");
        }
    }

    private static void checkExistMovieSchedule(LocalDateTime time, int movieId) {
        if (!MovieRepository.checkValidTime(time, movieId)) {
            throw new ErrorCustomException("상영시간이 지났습니다.");
        }
    }

    private static void checkIsOneHourWithin(LocalDateTime time) {
        LocalDateTime nowTime = DateTimeUtils.createDateTime("2019-04-16 15:30");
        if (!DateTimeUtils.isOneHourWithinRange(nowTime, time)) {
            throw new ErrorCustomException("현재 시간으로부터 1시간 이내의 영화만 예약해 주세요.");
        }
    }

    public static int inputHowMany(int movieId, LocalDateTime movieReservationTime) {
        while (true) {
            try {
                System.out.println("## 예약할 인원을 입력해 주세요.");
                String peopleInput = scanner.nextLine().trim();
                int people = checkPeopleIsNumber(peopleInput);
                return checkValidCapacity(movieId, movieReservationTime, people);
            } catch (ErrorCustomException errorCustomException) {
                System.out.println(errorCustomException.getMessage());
            }
        }
    }

    private static int checkValidCapacity(int movieId, LocalDateTime movieReservationTime,
        int people) {
        if (people < 1) {
            throw new ErrorCustomException("1이상을 입력해 주세요.");
        }
        if (!MovieRepository.checkValidCapacity(movieReservationTime, movieId, people)) {
            throw new ErrorCustomException("수용범위를 벗어납니다.");
        }
        return people;
    }

    private static int checkPeopleIsNumber(String peopleInput) {
        try {
            return Integer.parseInt(peopleInput);
        } catch (NumberFormatException n) {
            throw new ErrorCustomException("숫자를 입력해 주세요.");
        }
    }

    public static String inputMoreReservationOrPay(){
        System.out.println("## 1. 예약종료, 2. 추가예약 중 선택해주세요.");
        String input = scanner.nextLine().trim();
        if( !(input.equals("1")|| input.equals("2")) ){
            throw new ErrorCustomException("1,2중에서 입력해 주세요");
        }
        return input;
    }
}
