package org.generation.italy.pokemon.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.generation.italy.pokemon.model.entities.Pokemon;
import org.generation.italy.pokemon.model.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findById(@PathVariable long id){
        Optional<Pokemon> opt = pokemonService.findById(id);
        if (opt.isPresent()){
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) throws URISyntaxException {
        Pokemon createdPokemon = pokemonService.createPokemon(pokemon);
        URI location = new URI("/api/pokemon/" + createdPokemon.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
         /* equivalenti alla linea 39
        ResponseEntity.BodyBuilder bd = ResponseEntity.status(HttpStatus.CREATED);
        ResponseEntity r = bd.body(createdPokemon);
        return r;
        */
        return new ResponseEntity<>(createdPokemon, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable long id, @RequestBody Pokemon pokemon){
        if (id!= pokemon.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Pokemon> oldOpt = pokemonService.updatePokemon(pokemon);
        if (oldOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(oldOpt.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> deletePokemon(@PathVariable long id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        if(pokemon.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        pokemonService.deletePokemon(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/letter/{c}")
    public ResponseEntity<List<Pokemon>> findByContainingLetter(@PathVariable String c) {
        List<Pokemon> list = pokemonService.getPokemonWithLetter(c);
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/hp/{n}")
    public ResponseEntity<List<Pokemon>> findByHPMoreThan(@PathVariable int n) {
        List<Pokemon> list = pokemonService.getPokemonWithHPMoreThan(n);
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
