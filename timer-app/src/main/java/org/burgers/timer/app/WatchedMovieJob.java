package org.burgers.timer.app;

import org.burgers.timer.domain.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WatchedMovieJob {
    @Autowired
    private Repository repository;

    public void run() {
        repository.markAsWatched();
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
