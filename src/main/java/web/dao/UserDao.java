package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {
    //Решение с помощью EntityManager
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<User> listUsers() {
        //select всех user из БД
        TypedQuery<User> query = entityManager.createQuery("SELECT user from User as user", User.class);
        return query.getResultList();
    }

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

    @Transactional
    public User getUserById(Long id) {
        //поиск user по id
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void deleteUser(Long id) {
        //удаление user по id
        entityManager.remove(getUserById(id));
    }
}
