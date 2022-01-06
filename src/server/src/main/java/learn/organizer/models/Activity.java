package learn.organizer.models;


import java.time.LocalDate;
import java.time.LocalTime;

public class Activity {

    private int activityId;
    private String activityName;
    private String location;
    private LocalDate date;
    private String time;
    private int max;
    private int min;

    private String createBy;
    private int userId;

    private String description;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUserId(int userId) {
        this.userId=userId;
    }

    public int getUserId() {
        return userId;
    }


    public void setDescription(String description) {
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}