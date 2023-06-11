import danekerscode.socialmediaapi.controller.UserController;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownServiceException;

@SpringBootTest
public class UserTests {

    private UserController userController;

    @Test
    public void check() {
        try {
            Assert.assertNotNull(userController.getById(43));
        } catch (UnknownServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
