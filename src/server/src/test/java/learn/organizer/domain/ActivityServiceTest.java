package learn.organizer.domain;

import learn.organizer.data.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ActivityServiceTest {

    @Autowired
    ActivityService service;

    //autowired or MockBean
    @Autowired
    ActivityRepository agencyRepository;

    @Test
    void ShouldAddActivity() {
    }

    @Test
    void shouldEditActivity() {
    }

    @Test
    void deleteById() {
    }
}
