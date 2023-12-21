package org.generation.italy.pokemon.model.services;

import org.generation.italy.pokemon.model.entities.Pokemon;
import org.generation.italy.pokemon.model.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PokemonServiceImpl implements PokemonService{
    private final PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Optional<Pokemon> findById(long id) {
        return pokemonRepository.findById(id);
    }

    @Override
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public Optional<Pokemon> updatePokemon(Pokemon pokemon) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(pokemon.getId());
        if (optionalPokemon.isEmpty()){
            return optionalPokemon;
        }
        Pokemon found = optionalPokemon.get();
        Pokemon old = new Pokemon(found.getId(), found.getName(), found.getPuntiVita(), found.getTipo());
        pokemonRepository.save(pokemon);
        return Optional.of(old);
    }

    @Override
    public void deletePokemon(long pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }

    @Override
    public List<Pokemon> getPokemonWithLetter(String c) {
        return pokemonRepository.findByNameContainingIgnoreCase(c);
    }

    @Override
    public List<Pokemon> getPokemonWithHPMoreThan(int n) {
        return pokemonRepository.findByPuntiVitaGreaterThanEqual(n);
    }
}
