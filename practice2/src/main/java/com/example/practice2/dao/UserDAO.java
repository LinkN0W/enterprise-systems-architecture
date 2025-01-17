package com.example.practice2.dao;


import com.example.practice2.HibernateUtil;
import com.example.practice2.entities.User;
import org.hibernate.LockMode;

import java.util.UUID;

public class UserDAO {

    public UUID add(User user) {
        return (UUID) HibernateUtil.getSessionFactory()
                .getCurrentSession()
                .save(user);
    }

    public void update(User user) {
        HibernateUtil.getSessionFactory().getCurrentSession().update(user);
    }

    public User get(UUID id) {
        return HibernateUtil.getSessionFactory()
                .getCurrentSession()
                .get(User.class, id, LockMode.PESSIMISTIC_READ);
    }

    public Iterable<User> getAll() {
        return HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery("From " + User.class.getSimpleName()).getResultList();
    }

}
