package org.generation.italy.pokemon.model.services;

import org.generation.italy.pokemon.model.entities.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonService {

    Pokemon createPokemon(Pokemon pokemon);
    Optional<Pokemon> findById(long id);
    List<Pokemon> getAll();
    Optional<Pokemon> updatePokemon(Pokemon pokemon);
    void deletePokemon(long pokemonId);
    List<Pokemon> getPokemonsWithNameContaining(String c);
    List<Pokemon> getPokemonsWithHPMoreThan(int n);
    List<Pokemon> getPokemonsWithAllData(int n, String c);
}
