package view;

import domain.MovieRepository;
import java.util.Scanner;
import utils.ErrorCustomException;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        while (true) {
            try {
                System.out.println("## 예약할 영화를 선택하세요.");
                String idInput = scanner.nextLine().trim();
                return checkIdNumber(idInput);
            } catch (ErrorCustomException errorCustomException){
                System.out.println(errorCustomException.getMessage());
            }
        }
    }

    private static int checkIdNumber(String idInput) {
        try{
            int id = Integer.parseInt(idInput);
            checkValidMovieId(id);
            return id;
        } catch (NumberFormatException n){
            throw new ErrorCustomException("숫자를 입력해 주세요.");
        }
    }

    private static void checkValidMovieId(int id) {
        if(!MovieRepository.checkValidMovieId(id)){
            throw new ErrorCustomException("예매 가능한 영화가 없습니다.");
        }
    }
}
