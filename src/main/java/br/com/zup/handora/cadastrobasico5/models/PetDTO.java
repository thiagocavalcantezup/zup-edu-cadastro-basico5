package br.com.zup.handora.cadastrobasico5.models;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class PetDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    @NotBlank
    private String raca;

    public PetDTO() {}

    public PetDTO(@NotBlank String nome, @NotNull @PastOrPresent LocalDate dataNascimento,
                  @NotNull TipoPet tipo, @NotBlank String raca) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
        this.raca = raca;
    }

    public Pet paraPet() {
        return new Pet(nome, dataNascimento, tipo, raca);
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public String getRaca() {
        return raca;
    }

}
