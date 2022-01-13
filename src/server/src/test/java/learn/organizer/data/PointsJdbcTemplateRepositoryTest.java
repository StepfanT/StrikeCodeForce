package learn.organizer.data;

import learn.organizer.models.Points;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PointsJdbcTemplateRepositoryTest {

    @Autowired
    PointsJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldGetAllPointsByUserId(){
        List<Points> point = repository.getAllPointsByUserId(1);
        assertNotNull(point);
        int p = 0;
        for(int i = 0; i < point.size(); i++){
            p += point.get(i).getPoint();
        }
        assertEquals(8, p);
    }

    @Test
    void shouldAddPoints(){
        Points point = new Points(1,2,1,false);

        boolean actual = repository.addPoints(point);
        
        assertEquals(true, actual);



    }

    @Test
    void shouldEditPoints(){
        Points point = new Points(3,1,1,true);

        boolean actual = repository.editPoints(point);

        assertEquals(true, actual);


    }

    @Test
    void shouldDeletePoints(){
        Points point = new Points(3,1,1,true);

        boolean actual = repository.deletePoints(1, 1);

        assertEquals(true, actual);
        
    }

    @Test
    void shouldDeleteAllPointsFromUser(){
        Points point = new Points(3,1,1,true);

        boolean actual = repository.deleteAllPointsFromUser(1);

        assertEquals(true, actual);
                                              
    }



    

}
