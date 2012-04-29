package org.burgers.timer.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:contexts/DomainContext.xml"})
public class RepositoryImplTest {
    @Autowired
    private Repository repository;

    @Before
    public void setUp(){
        repository.deleteAll();
    }

    @Test
    public void save(){
        Movie movie = new Movie();
        movie.setTitle("Jaws");
        movie.setWatched(true);
        repository.save(movie);
        List results = repository.findAll();
        assertEquals(results.size(), 1);
        Movie foundMovie = (Movie) results.get(0);
        assertTrue(foundMovie.getId() > 0);
        assertEquals(foundMovie.getTitle(), "Jaws");
        assertTrue(foundMovie.isWatched());
    }

    @Test
    public void findAll_returns_emptyList(){
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    public void findById_returns_null(){
        assertNull(repository.findById(200));
    }

    @Test
    public void delete(){
        Movie movie = new Movie();
        movie.setTitle("Jaws");
        repository.save(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Jaws 2");
        repository.save(movie2);

        List results = repository.findAll();
        assertEquals(results.size(), 2);
        repository.delete(movie);

        List results2 = repository.findAll();
        assertEquals(results2.size(), 1);

        Movie foundMovie = (Movie) results2.get(0);
        assertEquals(foundMovie.getTitle(), "Jaws 2");
        assertFalse(foundMovie.isWatched());
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
