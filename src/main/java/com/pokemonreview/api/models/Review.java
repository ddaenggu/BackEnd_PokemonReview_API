package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String content;

    private int stars;

    @ManyToOne(fetch = FetchType.LAZY) // Pokemon과 Review가 1:n 관계, FetchType이 LAZY일 때 getPokemon(); 할 때 pokemon을 가지고 옴
    @JoinColumn(name = "pokemon_id") // Pokemon의 pokemon_id 컬럼을 본다
    private Pokemon pokemon;
}