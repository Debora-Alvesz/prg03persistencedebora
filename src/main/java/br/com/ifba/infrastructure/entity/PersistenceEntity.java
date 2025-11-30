package br.com.ifba.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


// Indica que essa classe será herdada por outras entidades
@MappedSuperclass
@NoArgsConstructor //Gera o construtor vazio (necessário para o JPA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //garante que um objeto é igual ao outro somente se id for igual
public abstract class PersistenceEntity {

    // ID único gerado automaticamente para cada registro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @EqualsAndHashCode.Include //Informa ao Lombok para usar este campo no equals/hashCode
    private Long id;
}
