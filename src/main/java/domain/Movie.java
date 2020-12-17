package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import utils.ErrorCustomException;

public class Movie {
    private static final char NEW_LINE = '\n';

    private final int id;
    private final String name;
    private final int price;

    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public Movie(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append(playSchedule);
        }
        return id + " - " + name + ", " + price + "원" + NEW_LINE
            + sb.toString();
    }

    public int getId() {
        return id;
    }

    public int getPrice(){
        return price;
    }

    public List<PlaySchedule> getPlaySchedules() {
        return Collections.unmodifiableList(playSchedules);
    }

    //public boolean checkPlaySchedule(LocalDateTime wantTime) {
//        return playSchedules.stream().anyMatch(schedule -> Objects.equals(schedule.getStartDateTime(), wantTime));
//    }
}
