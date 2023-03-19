package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> uuuu() {
        return userDao.uuu();
    }

    @Transactional
    @Override
    public void deleteAllUsers(){
        userDao.deleteAllUsers();
    }

    @Transactional
    @Override
    public User findOwner(String car_name, String car_series) {
        return userDao.findOwner(car_name, car_series);
    }

}