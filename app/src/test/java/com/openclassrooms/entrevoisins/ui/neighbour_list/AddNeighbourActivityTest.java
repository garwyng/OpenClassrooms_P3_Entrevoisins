package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertTrue;

import androidx.test.filters.LargeTest;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@LargeTest
@RunWith(JUnit4.class)
public class AddNeighbourActivityTest {

        private NeighbourApiService service;

        @Before
        public void setup() {
            service = DI.getNewInstanceApiService();
        }

        @Test
        public void addNeighbourTest() {
            Neighbour neighbour = new Neighbour(service.getNeighbours().size(),"sebastien","","Rue de l'exemple","07.06.04.05.01","ceci esr une test");
            List<Neighbour> neighbours = service.getNeighbours();
            service.createNeighbour(neighbour);
            assertTrue(neighbours.contains(neighbour));    }
    }
