package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     *
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * create a neighbour
     *
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add or delete a favorite neighbour
     * @param neighbour
     */
    void manageFavorite(Neighbour neighbour);

    /**
     * get the favorites neighbours
     * @return
     */
    List<Neighbour> getFavoritesNeighbours();
}

