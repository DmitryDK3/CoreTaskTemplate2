package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS myusers(id INT PRIMARY KEY AUTO_INCREMENT , name varchar(255), lastName varchar(255), age INT  )").executeUpdate();
            System.out.println("как будто создали таблицу");
            tr.commit();
            session.disconnect();
    }

    @Override
    public void dropUsersTable() {
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS myusers").executeUpdate();
            System.out.println("как будто удалили таблицу");
            tr.commit();
            session.disconnect();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            session.save(new User(name,lastName,age));
            System.out.printf("User с именем – %s добавлен в базу данных", name);
            tr.commit();
            session.disconnect();
    }

    @Override
    public void removeUserById(long id) {
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.delete(user);
            System.out.printf("Юзер %d удален",id);
            tr.commit();
            session.disconnect();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            list =  (List<User>) session.createQuery("from User").list();

            System.out.println("как будто получили лист юзеров");
            tr.commit();
            session.disconnect();
        return list;
    }

    @Override
    public void cleanUsersTable() {
            session = Util.setUp().openSession();
            Transaction tr = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            System.out.println("как будто очистили таблицу");
            tr.commit();
            session.disconnect();
    }
}
