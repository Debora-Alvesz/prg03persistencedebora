package br.com.ifba.infrastructure.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// Indica que essa classe será herdada por outras entidades
@MappedSuperclass
public abstract class PersistenceEntity {

    // ID único gerado automaticamente para cada registro
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Getter e Setter do ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
