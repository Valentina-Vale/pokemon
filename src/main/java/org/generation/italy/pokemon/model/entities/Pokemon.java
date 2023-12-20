package org.generation.italy.pokemon.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(generator="pokemon_generator",strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="pokemon_generator",sequenceName="id_seq", allocationSize=1)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="punti_vita")
    private long puntiVita;
    @Column(name="tipo")
    private String tipo;

    public Pokemon (){
    }

    public Pokemon (long id, String name, long puntiVita, String tipo){
        this.id = id;
        this.name = name;
        this.puntiVita = puntiVita;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPuntiVita() {
        return puntiVita;
    }

    public void setPuntiVita(long puntiVita) {
        this.puntiVita = puntiVita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
