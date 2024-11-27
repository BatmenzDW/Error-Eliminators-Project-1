package com.revature.planetarium.repository;

import com.revature.planetarium.Utility;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

public class UserDaoTest {
    // set the type to the interface
    private UserDao userDao;

    private static String validGetUserUsername;
    private static User validGetUserData;
    private static String invalidGetUser;
    private static User invalidNewUser;
    private static User validNewUser;
    private static User longUsernameUser;
    private static User longPasswordUser;


    @BeforeClass
    public static void setupTestData(){
        validGetUserUsername = "Batman";
        validGetUserData = new User(1,"Batman","I am the night");
        invalidGetUser = "Joker";
        invalidNewUser = new User(2, "Batman", "I am the night");
        validNewUser = new User(2, "BatmanAndRobin", "JokerAndHarley");
        longUsernameUser = new User(2, "BatmanAndRobinNaNaNaNaNaNaNaNaNaNaNaNaNaNa", "JokerAndHarley");
        longPasswordUser = new User(2, "BatmanAndRobin", "JokerAndHarleyHaHaHaHaHaHaHaHaHaHaHaHaHaHa");

    }

    @Before
    public void setupTestObjects(){
        Utility.main(new String[]{});
        // initialize the implementation class
        userDao = new UserDaoImp();
    }

    @Test
    public void findUserByUsernamePositiveTest(){
        /*
            Optionals are useful when you can't guarantee what data is going to be returned
            by a method. It gives you a consistent return object you can then perform validation
            on to determine what action to take, typically determined by whether your optional
            contains an object of the expected type which is indicated by a Generic declaration
         */
        Optional<User> result = userDao.findUserByUsername(validGetUserUsername);
        /*
            Once you have your results back you need to validate the results are what you expect.
            There are multiple ways you can do this, choose a way that makes sense to you and
            your team, and ideally covers as many edge cases you can think of
         */
        User user = result.get();
        Assert.assertEquals(validGetUserData, user);
    }

    @Test
    public void findUserByUsernameNegativeTest(){
        /*
            Since we expect no user data in our negative test we only need to set up a
            non-existent username for our test, pass it to the get method, and validate
            the optional returned is empty
         */
        Optional<User> result = userDao.findUserByUsername(invalidGetUser);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void registerUserAlreadyExists(){
        /*
        Test fail expected: the user already exists so it should not be
        created. A unique constraint failed in SQL accordingly.
         */
        Optional<User> result = userDao.createUser(invalidNewUser);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void registerUserDoesNotExist(){
        Optional<User> result = userDao.createUser(validNewUser);
        User user = result.get();

        Assert.assertEquals(validNewUser, user);
    }

    @Test
    public void longUsernameRegister(){
        /*
        Test fail expected: the username is too long according to the
        system requirements. The check constraint fails in SQL.
         */
        Optional<User> result = userDao.createUser(longUsernameUser);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void longPasswordRegister(){
        /*
        Test fail expected: the password is too long according to the
        system requirements. The check constraint fails in SQL.
         */
        Optional<User> result = userDao.createUser(longPasswordUser);
        Assert.assertTrue(result.isEmpty());
    }

}