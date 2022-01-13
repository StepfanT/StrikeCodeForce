package learn.organizer.domain;


import learn.organizer.data.PointsRepository;
import learn.organizer.models.Points;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PointsServiceTest {

    @Autowired
    PointsService service;

    @MockBean
    PointsRepository repository;

    @Test
    void shouldGetPointsByUserId(){
        List<Points> points=new ArrayList<>();
        points.add(new Points(1,1,1,true));
        points.add(new Points(2,1,2,true));
        points.add(new Points(1,1,3,true));
        int sum=0;
        for(Points point:points){
            sum+=point.getPoint();
        }

        assertEquals(4,sum);
    }

    @Test
    void shouldAddPointsAddPoints(){
        Points points=new Points(1,1,1,true);
        when(repository.addPoints(points)).thenReturn(true);

        assertEquals(true,repository.addPoints(points));
    }

    @Test
    void shouldEditPoints(){
        Points points=new Points(1,1,1,true);
        when(repository.editPoints(points)).thenReturn(true);

        assertEquals(true,repository.editPoints(points));
    }

    @Test
    void shouldDeletePoints(){

        when(repository.deletePoints(1,1)).thenReturn(true);

        assertEquals(true,repository.deletePoints(1,1));
    }

    @Test
    void shouldDeleteAlPointsFromUser(){
        when(repository.deleteAllPointsFromUser(1)).thenReturn(true);

        assertEquals(true,repository.deleteAllPointsFromUser(1));
    }

}
