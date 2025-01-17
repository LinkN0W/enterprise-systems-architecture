package com.example.practice2.services;

import com.example.practice2.DBService;
import com.example.practice2.dao.UserDAO;
import com.example.practice2.entities.Book;
import com.example.practice2.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.UUID;

@Service
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
