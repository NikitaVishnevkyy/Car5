package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      carService.add(new Car("BMW", "320"));
      carService.add(new Car("BMW", "X5"));
      carService.add(new Car("BMW", "750"));
      carService.add(new Car("BMW", "X6"));

      UserService userService = context.getBean(UserService.class);
      userService.deleteAllUsers();
      List<Car> cars = carService.cccc();

      userService.add(new User("Sasha", "Sasha", "Sasha@mail.ru", cars.get(0)));
      userService.add(new User("Nikita", "Nikita", "Nikita@mail.ru", cars.get(1)));
      userService.add(new User("Vlad", "Vlad", "Vlad@mail.ru", cars.get(2)));
      userService.add(new User("Olga", "Olga", "Olga@mail.ru", cars.get(3)));

      List<User> uu = userService.uuuu();
      for (User user : uu) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println("Найти \"BMW X5\"");
      System.out.println(userService.findOwner("BMW", "X5"));
      context.close();
   }
}
