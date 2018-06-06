package br.univille.projcolabassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class InstituicaoController {


@Controller
@RequestMapping("/instituicao")

@GetMapping("")
public ModelAndView index() {

return new ModelAndView("instituicao/index");
}
}