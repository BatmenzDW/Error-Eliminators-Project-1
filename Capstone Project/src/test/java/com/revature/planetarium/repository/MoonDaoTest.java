package com.revature.planetarium.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.revature.planetarium.Utility;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MoonDaoTest {
    private MoonDao moonDao;

    private static Moon validNewMoon;
    private static Moon invalidNewMoon;
    private static Moon validMoon;
    private static Moon invalidMoon;

    @BeforeClass
    public static void setupTestData(){
        validNewMoon = new Moon(3, "Pluto", 1);
        invalidNewMoon = new Moon(1, "Luna", 1);
        validMoon = new Moon(2, "Titan", 2);
        invalidMoon = new Moon(7, "Pluto", 12);
    }

    @Before
    public void setupTestObjects(){
        Utility.main(new String[]{});
        // initialize the implementation class
        moonDao = new MoonDaoImp();
    }

    @Test
    public void createMoonPositiveTest(){
        Optional<Moon> result = moonDao.createMoon(validNewMoon);
        Moon moon = result.get();

        Assert.assertEquals(validNewMoon, moon);
    }

    @Test
    public void createMoonNegativeTest(){
        Optional<Moon> result = moonDao.createMoon(invalidNewMoon);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void readMoonByIdPositiveTest(){
        Optional<Moon> result = moonDao.readMoon(1);
        Moon moon = result.get();
        Assert.assertEquals(1, moon.getMoonId());
    }

    @Test
    public void readMoonByIdNegativeTest(){
        Optional<Moon> result = moonDao.readMoon(78);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void readMoonByNamePositiveTest(){
        Optional<Moon> result = moonDao.readMoon(validMoon.getMoonName());
        Moon moon = result.get();
        Assert.assertEquals(validMoon.getMoonName(), moon.getMoonName());
    }

    @Test
    public void readMoonByNameNegativeTest(){
        Optional<Moon> result = moonDao.readMoon(invalidMoon.getMoonName());
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void readAllMoonsTest(){
        List<Moon> result = moonDao.readAllMoons();
        Assert.assertTrue(result.size() > 1);
    }

    @Test
    public void readMoonsByPlanetPositiveTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(validMoon.getOwnerId());
        Moon moon = result.get(0);

        Assert.assertEquals(validMoon.getOwnerId(), moon.getOwnerId());
    }

    @Test
    public void readMoonsByPlanetNegativeTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(invalidMoon.getMoonId());
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void deleteMoonByIdPositiveTest(){
        boolean result = moonDao.deleteMoon(validMoon.getMoonId());
        Assert.assertTrue(result);
    }

    @Test
    public void deleteMoonByIdNegativeTest(){
        boolean result = moonDao.deleteMoon(invalidMoon.getMoonId());
        Assert.assertFalse(result);
    }

    @Test
    public void deleteMoonByNamePositiveTest(){
        boolean result = moonDao.deleteMoon(validMoon.getMoonName());
        Assert.assertTrue(result);
    }

    @Test
    public void deleteMoonByNameNegativeTest(){
        boolean result = moonDao.deleteMoon(invalidMoon.getMoonName());
        Assert.assertFalse(result);
    }
}
