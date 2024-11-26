package com.revature.planetarium.controller;

import com.revature.planetarium.Utility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

public class ViewControllerTest {
    private ViewController viewController;

    private static String validPage;
    private static String invalidPage;

    @BeforeClass
    public static void setUpData() {
        validPage = "login.html";
        invalidPage = "index.html";
    }

    @Before
    public void setUp() {
        Utility.resetTestDatabase();

        viewController = new ViewController();
    }

    @Test
    public void loadPagePositiveTest(){
        try{
            String page = viewController.loadPage(validPage);
            Assert.assertTrue(page.contains("<title>Planetarium Login</title>"));
        }
        catch (IOException ioe){
            throw new AssertionError(ioe);
        }
    }

    @Test
    public void loadPageNegativeTest(){
        Assert.assertThrows(IOException.class, () -> viewController.loadPage(invalidPage));
    }

    
}
