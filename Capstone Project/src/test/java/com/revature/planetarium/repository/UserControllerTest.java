package com.revature.planetarium.repository;

import com.revature.planetarium.Utility;
import com.revature.planetarium.controller.UserController;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;
import io.javalin.http.Context;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.*;

public class UserControllerTest {
    // TODO: CREATE API TESTS TO CHECK FOR APPROPRIATE RESPONSE USING MOCKED CONTEXT
    // set the type to the interface
    private Context ctx = mock(Context.class);

    private UserController userController;
    private UserService userService;
    private UserDao userDao;

    private static User invalidNewUser;
    private static User validNewUser;
    private static User emptyUserNameUser;
    private static User emptyPasswordUser;
    private static User longUsernameUser;
    private static User longPasswordUser;
    private static User validUserLogin;
    private static User invalidUserLogin;

    @BeforeClass
    public static void setupTestData(){
        invalidNewUser = new User(2, "Batman", "I am the night");
        validNewUser = new User(2, "BatmanAndRobin", "JokerAndHarley");
        emptyUserNameUser = new User(2, "", "JokerAndHarley");
        emptyPasswordUser = new User(2, "BatmanAndRobin", "");
        longUsernameUser = new User(2, "BatmanAndRobinNaNaNaNaNaNaNaNaNaNaNaNaNaNa", "JokerAndHarley");
        longPasswordUser = new User(2, "BatmanAndRobin", "JokerAndHarleyHaHaHaHaHaHaHaHaHaHaHaHaHaHa");
        validUserLogin = new User(1, "Batman", "I am the night");
        invalidUserLogin = new User(4, "TestName", "TestPassword");
    }

    @Before
    public void setupTestObjects(){
        Utility.main(new String[]{});
        // initialize the implementation class
        userDao = new UserDaoImp();
        userService = new UserServiceImp(userDao);
        userController = new UserController(userService);
    }

    @Test
    public void registerUserAlreadyExists(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(invalidNewUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx); // the handler we're testing
        verify(ctx).status(400);
    }

    @Test
    public void registerUserDoesNotExist(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(validNewUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx); // the handler we're testing
        verify(ctx).status(201);
    }

    @Test
    public void registerEmptyUsernameUser(){
        /*
        According to software requirements, a username must be less than 30 characters,
        so an empty field username should be allowed but this test fails.
         */
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(emptyUserNameUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx);
        verify(ctx).status(201);
    }

    @Test
    public void registerEmptyPasswordUser(){
        /*
        According to software requirements, a password must be less than 30 characters,
        so an empty field username should be allowed but this test fails.
         */
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(emptyPasswordUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx);
        verify(ctx).status(201);
    }

    @Test
    public void registerLongUsernameUser(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(longUsernameUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx);
        verify(ctx).status(400);
    }

    @Test
    public void registerLongPasswordUser(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(longPasswordUser);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.createUser(ctx);
        verify(ctx).status(400);
    }

    @Test
    public void loginValidUser(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(validUserLogin);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.login(ctx);
        verify(ctx).status(202);
    }

    @Test
    public void loginInvalidUser(){
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(invalidUserLogin);

        User user = ctx.bodyAsClass(User.class);
        System.out.println(user);

        userController.login(ctx);
        verify(ctx).status(401);
    }
}
