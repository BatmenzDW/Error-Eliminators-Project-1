package com.revature.planetarium.repository;

import com.revature.planetarium.Utility;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class PlanetDaoTest {
    // set the type to the interface
    private PlanetDao planetDao;

    @BeforeClass
    public static void setupTestData(){
        // Cannot test createPlanet as there is no constructor for Planet.
    }

    @Before
    public void setupTestObjects(){
        Utility.main(new String[]{});
        // initialize the implementation class
        planetDao = new PlanetDaoImp();
    }

    @Test
    public void readPlanetByIdPositiveTest(){
        Optional<Planet> result = planetDao.readPlanet(1);
        Planet planet = result.get();
        Assert.assertEquals(1, planet.getPlanetId());
    }

    @Test
    public void readPlanetByIdNegativeTest(){
        Optional<Planet> result = planetDao.readPlanet(77);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void readPlanetByNamePositiveTest(){
        Optional<Planet> result = planetDao.readPlanet("Mars");
        Planet planet = result.get();
        Assert.assertEquals("Mars", planet.getPlanetName());
    }

    @Test
    public void readPlanetByNameNegativeTest(){
        Optional<Planet> result = planetDao.readPlanet("Nessus");
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void readAllPlanetsTest(){
        List<Planet> result = planetDao.readAllPlanets();
        Assert.assertTrue(result.size() > 1);
    }

    @Test
    public void readPlanetsByOwnerPositiveTest(){
        List<Planet> result = planetDao.readPlanetsByOwner(1);
        Assert.assertTrue(result.size() > 1);
    }

    @Test
    public void readPlanetsByOwnerNegativeTest(){
        List<Planet> result = planetDao.readPlanetsByOwner(88);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void deletePlanetByIdPositiveTest(){
        boolean result = planetDao.deletePlanet(1);
        Assert.assertTrue(result);
        // Test fails due to a foreign key constraint from a moon.
    }

    @Test
    public void deletePlanetByIdNegativeTest(){
        boolean result = planetDao.deletePlanet(76);
        Assert.assertFalse(result);
    }

    @Test
    public void deletePlanetByNamePositiveTest(){
        boolean result = planetDao.deletePlanet("Mars");
        Assert.assertTrue(result);
        // Test fails due to a foreign key constraint from a moon.
    }

    @Test
    public void deletePlanetByNameNegativeTest(){
        boolean result = planetDao.deletePlanet("Nessus");
        Assert.assertFalse(result);
    }
}
