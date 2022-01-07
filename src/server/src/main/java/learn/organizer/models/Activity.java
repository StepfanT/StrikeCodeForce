package learn.organizer.models;


import java.time.LocalDate;
import java.time.LocalTime;

public class Activity {

    private int activityId;
    private String activityName;
    private String location;
    private LocalDate date;
    private String time;
    private int maxParticipant;
    private int minParticipant;

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
        return maxParticipant;
    }

    public void setMax(int max) {
        this.maxParticipant = max;
    }

    public int getMin() {
        return minParticipant;
    }

    public void setMin(int min) {
        this.minParticipant = min;
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

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", max=" + maxParticipant +
                ", min=" + minParticipant +
                ", createBy='" + createBy + '\'' +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}