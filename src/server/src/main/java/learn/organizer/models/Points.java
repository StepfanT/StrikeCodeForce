package learn.organizer.models;

public class Points {
    int point;
    int userId;
    int activityId;
    boolean completed;

    public Points(int point,int userId,int activityId,boolean completed){
        this.point=point;
        this.userId=userId;
        this.activityId=activityId;
        this.completed=completed;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
