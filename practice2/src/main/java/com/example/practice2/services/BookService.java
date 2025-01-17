package com.example.practice2.services;

import com.example.practice2.DBService;
import com.example.practice2.HibernateUtil;
import com.example.practice2.dao.BookDAO;
import com.example.practice2.dao.UserDAO;
import com.example.practice2.entities.Book;
import com.example.practice2.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.UUID;

@Service
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
