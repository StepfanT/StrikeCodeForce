package learn.organizer.models;

import java.util.Objects;

public class AppRole {
    private int AppRoleId;
    private String name;

    public AppRole(){
    }
    public AppRole(String name){
        this.name=name;
    }
    public AppRole(int appRoleId,String name){
        this.AppRoleId=appRoleId;
        this.name=name;
    }
    public int getAppRoleId() {
        return AppRoleId;
    }

    public void setAppRoleId(int appRoleId) {
        AppRoleId = appRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return AppRoleId == appRole.AppRoleId && Objects.equals(name, appRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AppRoleId, name);
    }
}
