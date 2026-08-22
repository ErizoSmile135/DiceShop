import com.dice.shop.controller.MainController;
import com.dice.shop.repository.GoodRepository;
import com.dice.shop.repository.UserRepository;
import com.dice.shop.repository.impl.GoodRepositoryImpl;
import com.dice.shop.repository.impl.UserRepositoryImpl;
import com.dice.shop.service.AuthService;
import com.dice.shop.service.GoodService;
import com.dice.shop.service.UserService;


public class Main {

    public static void main(String[] args) {
        // Repository
        UserRepository userRepository = new UserRepositoryImpl();
        GoodRepository goodRepository = new GoodRepositoryImpl();
        // Service
        UserService userService = new UserService(userRepository);
        GoodService goodService = new GoodService(goodRepository);
        AuthService authService = new AuthService(userService);
        // Controller
        MainController mainController =
                new MainController(
                        userService,
                        goodService,
                        authService
                );

        mainController.start();
    }
}