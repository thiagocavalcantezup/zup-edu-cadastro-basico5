package br.com.zup.handora.cadastrobasico5.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    @Column(nullable = false)
    private String raca;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Pet() {}

    public Pet(String nome, @PastOrPresent LocalDate dataNascimento, TipoPet tipo, String raca) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
        this.raca = raca;
    }

    public Long getId() {
        return id;
    }

}
