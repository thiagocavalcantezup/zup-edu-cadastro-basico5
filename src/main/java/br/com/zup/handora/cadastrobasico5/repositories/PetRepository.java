package br.com.zup.handora.cadastrobasico5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.handora.cadastrobasico5.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
