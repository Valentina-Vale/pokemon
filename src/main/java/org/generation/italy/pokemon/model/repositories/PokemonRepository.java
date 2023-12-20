package org.generation.italy.pokemon.model.repositories;

import org.generation.italy.pokemon.model.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {

    List<Pokemon> findByNameContaining(char c);
    List<Pokemon> findByPuntiVitaGreaterThanEqual(int n);

}
