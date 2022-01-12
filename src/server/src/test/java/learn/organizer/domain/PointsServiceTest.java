package learn.organizer.domain;


import learn.organizer.data.PointsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PointsServiceTest {

    @Autowired
    PointsService service;

    @MockBean
    PointsRepository repository;

    @Test
    void shouldGetPointsByUserId(){

    }

    @Test
    void shouldAddPointsAddPoints(){

    }

    @Test
    void shouldEditPoints(){

    }

    @Test
    void shouldDeletePoints(){

    }

    @Test
    void shouldDeleteAlPointsFromUser(){

    }

    @Test
    void shouldNotAddNegativePoints(){

    }
}
