package br.univille.projcolabassistant.controller;
import java.util.HashMap;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.univille.projcolabassistant.model.AccessorySize;

import br.univille.projcolabassistant.repository.AccessorySizeRepository;
@Controller
@RequestMapping("/accessorysize")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AcessorySizeController {
   @Autowired
   private AccessorySizeRepository accessorysizeRepository;
   
   @GetMapping("")
   public ModelAndView index() {
	   List<AccessorySize> listaccessorysize = this.accessorysizeRepository.findAll();
	   
	   
	   return new ModelAndView("accessorysize/index","listaccessorysize",listaccessorysize);
	   
	   
   }
   @GetMapping("/novo")
   public ModelAndView createForm(@ModelAttribute AccessorySize accessorysize) {
       HashMap<String, Object> dados = new HashMap<String, Object>();
       dados.put("accessorysize",accessorysize);
       
       return new ModelAndView("accessorysize/form",dados);
   }
   @PostMapping(params="form")
   public ModelAndView save(@Valid AccessorySize accessorysize, BindingResult result, RedirectAttributes redirect) {
       
	   accessorysize = this.accessorysizeRepository.save(accessorysize);
       
       return new ModelAndView("redirect:/accessorysize");
   }
   @GetMapping(value="/update/{id}")
   public ModelAndView alterarForm(@PathVariable("id") AccessorySize accessorysize) {
       HashMap<String, Object> dados = new HashMap<String, Object>();
       dados.put("accessorysize",accessorysize);
       return new ModelAndView("accessorysize/form",dados);
   }
   @GetMapping(value="delete/{id}")
   public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
       this.accessorysizeRepository.deleteById(id);
       return new ModelAndView("redirect:/accessorysize");
   }
}
