package org.burgers.timer.app;

import org.burgers.timer.domain.Movie;
import org.burgers.timer.domain.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:contexts/TimerContext.xml"})
public class WatchedMovieJobTest {
    @Autowired
    private WatchedMovieJob watchedMovieJob;

    @Autowired
    private Repository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void run(){
        saveUnwatchedMovie("Jaws");
        saveUnwatchedMovie("Jaws 2");
        saveUnwatchedMovie("Jaws 3");

        watchedMovieJob.run();

        List results = repository.findAll();
        assertEquals(results.size(), 3);
        for (Object result : results) {
            Movie myMovie = (Movie) result;
            assertTrue(myMovie.isWatched());
        }
    }

    private void saveUnwatchedMovie(String title){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setWatched(false);
        repository.save(movie);
    }


    public void setWatchedMovieJob(WatchedMovieJob watchedMovieJob) {
        this.watchedMovieJob = watchedMovieJob;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
