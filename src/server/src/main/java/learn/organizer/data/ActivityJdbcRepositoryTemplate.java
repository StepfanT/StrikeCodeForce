package learn.organizer.data;

import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class ActivityJdbcRepositoryTemplate implements ActivityRepository{
    @Override
    public List<Activity> findAll() {
        return null;
    }

    @Override
    public Activity findByAppUserId(int userId) {
        return null;

//        public List<Activity> findByUserId(int userId) throws DataAccessException {
//            ArrayList<Activity> result = new ArrayList<>();
//            List<Activity> activities = findAll();
//            for(Activity activity : activities){
//                if(AppUser.getAppUserId() = )
    }
}
