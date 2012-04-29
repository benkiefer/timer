package org.burgers.timer.domain;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(Movie movie) {
        hibernateTemplate.saveOrUpdate(movie);
        hibernateTemplate.flush();
    }

    @Override
    public void delete(Movie movie) {
        hibernateTemplate.delete(movie);
    }

    @Override
    public Movie findById(long id) {
        Movie movie = new Movie();
        movie.setId(id);
        List movies = hibernateTemplate.findByExample(movie);
        if (movies.size() > 0){
            return (Movie) movies.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List findAll() {
        return hibernateTemplate.find("from Movie");
    }

    @Override
    public void deleteAll() {
        hibernateTemplate.bulkUpdate("delete from Movie");
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}
