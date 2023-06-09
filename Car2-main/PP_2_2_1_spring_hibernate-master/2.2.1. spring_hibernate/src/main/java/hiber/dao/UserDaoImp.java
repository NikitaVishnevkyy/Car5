package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> uuu() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void deleteAllUsers() {
      List<User> users = uuu();
      for (User user: users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Override
   public User findOwner(String car_name, String car_series) {
      TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where name = :car_name and series = :car_series")
              .setParameter("car_name", car_name)
              .setParameter("car_series", car_series);
      List<Car> findCarList = findCarQuery.getResultList();
      if (!findCarList.isEmpty()) {
         Car findCar = findCarList.get(0);
         List<User> ListUser = uuu();
         return ListUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
      }
      return null;
   }
}
