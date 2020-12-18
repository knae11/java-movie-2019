package view;

import domain.MovieRepository;
import domain.OrderRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import utils.DateTimeUtils;
import utils.ErrorCustomException;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        String idInput = scanner.nextLine().trim();
        return checkIdNumber(idInput);
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
        System.out.println("## 예약할 시간를 선택하세요. 정확하게 같은 형식으로 입력");
        String timeInput = scanner.nextLine().trim();
        return checkTime(timeInput, movieId);

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

        System.out.println("## 예약할 인원을 입력해 주세요.");
        String peopleInput = scanner.nextLine().trim();
        int people = checkIsNumber(peopleInput);
        return checkValidCapacity(movieId, movieReservationTime, people);

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

    private static int checkIsNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException n) {
            throw new ErrorCustomException("숫자를 입력해 주세요.");
        }
    }

    public static String inputMoreReservationOrPay() {
        System.out.println("## 1. 예약종료, 2. 추가예약 중 선택해주세요.");
        String input = scanner.nextLine().trim();
        if (!(input.equals("1") || input.equals("2"))) {
            throw new ErrorCustomException("1,2중에서 입력해 주세요");
        }
        return input;
    }

    public static int inputPoint() {
        System.out.println("## 포인트를 입력해 주세요.");
        String pointInput = scanner.nextLine().trim();
        int point = checkIsNumber(pointInput);
        checkValidPoint(point);
        return point;

    }

    private static void checkValidPoint(int point) {
        checkOverMoviePrice(point);
        checkMinZero(point);

    }

    private static void checkMinZero(int point) {
        if (point < 0) {
            throw new ErrorCustomException("0이상의 정수만 입력해 주세요.");
        }
    }

    private static void checkOverMoviePrice(int point) {
        if (!OrderRepository.overTotalPrice(point)) {
            throw new ErrorCustomException("포인트 사용은 영화 총 금액을 넘을 수 없습니다.");
        }
    }

    public static String inputPayment() {
        System.out.println("## 결제 방법을 선택해 주세요. 1: 카드, 2: 현금");
        String payment = scanner.nextLine().trim();
        checkValidPayment(payment);
        return payment;
    }

    private static void checkValidPayment(String payment) {
        if (!(payment.equals("1") || payment.equals("2"))) {
            throw new ErrorCustomException("1,2 중에서만 입력해 주세요.");
        }
    }
}
