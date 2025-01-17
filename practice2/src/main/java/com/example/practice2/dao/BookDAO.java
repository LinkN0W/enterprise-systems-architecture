package com.example.practice2.dao;

import com.example.practice2.HibernateUtil;
import com.example.practice2.entities.Book;
import com.example.practice2.entities.User;
import org.hibernate.LockMode;

import java.util.UUID;

public class BookDAO {

    public UUID add(Book book) {
        return (UUID) HibernateUtil.getSessionFactory()
                .getCurrentSession()
                .save(book);
    }

    public Book get(UUID id) {
        return HibernateUtil.getSessionFactory()
                .getCurrentSession()
                .get(Book.class, id, LockMode.PESSIMISTIC_READ);
    }

    public void update(Book book) {
        HibernateUtil.getSessionFactory().getCurrentSession().update(book);
    }

    public Iterable<Book> getAll() {
        return HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery("From " + Book.class.getSimpleName()).getResultList();
    }
}
