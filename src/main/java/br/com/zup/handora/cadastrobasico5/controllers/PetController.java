package br.com.zup.handora.cadastrobasico5.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.cadastrobasico5.models.Pet;
import br.com.zup.handora.cadastrobasico5.models.PetDTO;
import br.com.zup.handora.cadastrobasico5.repositories.PetRepository;

@RestController
@RequestMapping(PetController.BASE_URI)
public class PetController {

    public final static String BASE_URI = "/pets";

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid PetDTO petDTO,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Pet pet = petRepository.save(petDTO.paraPet());

        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                                           .buildAndExpand(pet.getId())
                                           .toUri();

        return ResponseEntity.created(location).build();
    }

}
