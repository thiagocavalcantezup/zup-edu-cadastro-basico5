package br.com.zup.handora.cadastrobasico5.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PetController.BASE_URI)
public class PetController {

    public final static String BASE_URI = "/pets";

}
