package com.example.demo1.services;

import com.example.demo1.DBService;
import com.example.demo1.dao.UserDAO;
import com.example.demo1.entity.Book;
import com.example.demo1.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.UUID;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public UUID addUser(User user){

        Transaction transaction = DBService.getTransaction();
        UUID uuid = null;
        try {
            uuid = userDAO.add(user);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return uuid;
    }

    public User getUser(UUID uuid){
        Transaction transaction = DBService.getTransaction();
        User user = null;
        try {
            user = userDAO.get(uuid);
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return user;
    }

    public void updateUser(User user){
        Transaction transaction = DBService.getTransaction();
        try {
             userDAO.update(user);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
    }

    public Iterable<User> getAllUsers(){
        Transaction transaction = DBService.getTransaction();
        Iterable<User> users = null;
        try {
            users = userDAO.getAll();
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
        }
        return users;
    }
}
