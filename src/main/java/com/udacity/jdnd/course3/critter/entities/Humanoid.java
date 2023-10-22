package com.udacity.jdnd.course3.critter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Humanoid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "humanoid_seq")
    @SequenceGenerator(name = "humanoid_seq", sequenceName = "humanoid_seq", allocationSize = 1)
    private Long id;

    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
