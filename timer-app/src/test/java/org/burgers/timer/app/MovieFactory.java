package org.burgers.timer.app;

import org.burgers.timer.domain.Movie;

public class MovieFactory {
    public Movie createFrom(String title, boolean watched){
        Movie movie = new Movie();
        movie.setWatched(watched);
        movie.setTitle(title);
        return movie;
    }
}
