package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    //Решение с помощью EntityManager
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> listUsers() {
        //select всех user из БД
        TypedQuery<User> query = entityManager.createQuery("SELECT user from User as user", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getId() == null) {
            //сохранение user в БД
            entityManager.persist(user);
        } else {
            //обновление user в БД
            entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        //поиск user по id
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        //удаление user по id
        entityManager.remove(getUserById(id));
    }

    //Решение с помощью SessionFactory
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Override
//    @Transactional
//    public List<User> listUsers() {
//        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//        return query.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void saveUser(User user) {
//        sessionFactory.getCurrentSession().saveOrUpdate(user);
//    }
//
//    @Override
//    @Transactional
//    public User getUserById(Long id) {
//        return sessionFactory.getCurrentSession().get(User.class, id);
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(Long id) {
//        sessionFactory.getCurrentSession().delete(getUserById(id));
//    }
}
