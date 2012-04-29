package org.burgers.timer.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbtMovie")
public class Movie implements Serializable {
    public Movie(){ }
    public Movie(String title, boolean watched){
        this.title = title;
        this.watched = watched;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieId")
    private long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Watched")
    private boolean watched;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
