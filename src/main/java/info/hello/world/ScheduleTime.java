package info.hello.world;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScheduleTime {

    @JsonProperty("day")
    private Day day;

    @JsonProperty("time")
    private int time;

    @JsonProperty("users")
    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public ScheduleTime(Day day, int time, List<String> users) {
        this.day = day;
        this.time = time;
        this.users = users;
    }

    ScheduleTime(){}


    public Day getDay() {
        return day;
    }


    public void setDay(Day day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
