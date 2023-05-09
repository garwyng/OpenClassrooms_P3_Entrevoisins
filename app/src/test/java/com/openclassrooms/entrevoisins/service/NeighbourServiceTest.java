package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import androidx.test.filters.LargeTest;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@LargeTest
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    public void addNeighbourTest() {
        Neighbour neighbour = new Neighbour(service.getNeighbours().size(),"sebastien","","Rue de l'exemple","07.06.04.05.01","ceci esr une test");
        List<Neighbour> neighbours = service.getNeighbours();
        service.createNeighbour(neighbour);
        assertTrue(neighbours.contains(neighbour));    }
    @Test
    public void manageFavorites() {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour = neighbours.get(0);
        service.manageFavorite(neighbour);
        assertTrue(service.getFavoritesNeighbours().contains(neighbour));
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    @Test
    public void getFavoritesNeighbours() {
        List<Neighbour> favoritesNeighbours = service.getFavoritesNeighbours();
        favoritesNeighbours.clear();
        service.manageFavorite(service.getNeighbours().get(0));
        assertTrue(service.getFavoritesNeighbours().contains(service.getNeighbours().get(0)));
    }
}
