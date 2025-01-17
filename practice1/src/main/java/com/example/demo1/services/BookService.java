package com.example.demo1.services;

import com.example.demo1.DBService;
import com.example.demo1.dao.BookDAO;
import com.example.demo1.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.UUID;


public class BookService {

    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public UUID addBook(Book book){

        Transaction transaction = DBService.getTransaction();
        UUID uuid = null;
        try {
            uuid = bookDAO.add(book);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return uuid;
    }

    public Book getBook(UUID uuid){
        Transaction transaction = DBService.getTransaction();
        Book book = null;
        try {
            book = bookDAO.get(uuid);
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return book;
    }

    public void updateBook(Book book){
        Transaction transaction = DBService.getTransaction();
        try {
            bookDAO.update(book);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
    }

    public Iterable<Book> getAllBooks(){
        Transaction transaction = DBService.getTransaction();
        Iterable<Book> books = null;
        try {
            books = bookDAO.getAll();
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return books;
    }

}
