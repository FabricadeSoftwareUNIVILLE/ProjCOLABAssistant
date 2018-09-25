package br.univille.projcolabassistant.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.AccessoryPhotoRepository;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;

@Controller
@RequestMapping("/assistiveaccessory")
public class AssistiveAccessoryController {
	@Autowired
	private AssistiveAccessoryRepository accessoryRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AccessoryPhotoRepository accessoryPhotoRepository;

	private String imgPath = "C:\\ProjCOLABAssistant\\src\\main\\resources\\static\\image\\assistiveaccessory\\";

	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessory> accessoriesList = this.accessoryRepository.findAll();

		return new ModelAndView("assistiveaccessory/index", "assistiveaccessories", accessoriesList);
	}

	@GetMapping("/new")
	public ModelAndView createForm(@ModelAttribute AssistiveAccessory assistiveaccessory) {
		List<Category> categories = categoryRepository.findAll();

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("assistiveaccessory", assistiveaccessory);
		dados.put("categories", categories);

		return new ModelAndView("assistiveaccessory/form", dados);
	}

	@PostMapping(value = "/new")
	public ModelAndView save(@RequestParam("photos[]") List<MultipartFile> files, @RequestParam("desc[]") List<String> descriptions, @Valid AssistiveAccessory assistiveAccessory) {
		try {
			int index = 0;
			List<AccessoryPhoto> photos = new ArrayList<>();
			
			for (MultipartFile file : files) {
				AccessoryPhoto accessoryPhoto = new AccessoryPhoto();
				byte[] bytes = file.getBytes(); 
				
				String imageName = new Timestamp(System.currentTimeMillis()).getTime() + "_" + assistiveAccessory.getId() + "_" + index + ".png";
				Path path = Paths.get(imgPath + imageName);
			
				accessoryPhoto.setURI(path.toString());
				
				if(!descriptions.isEmpty()) {
					accessoryPhoto.setDescription(descriptions.get(index));
				}
				else {
					accessoryPhoto.setDescription("");
				}
				
				if (accessoryPhoto != null) {
					accessoryPhotoRepository.save(accessoryPhoto);
				}
				
				photos.add(accessoryPhoto);
				
				Files.write(path, bytes);
				
				index++;
			}
			
			if (assistiveAccessory != null) {
				assistiveAccessory.setPhotoList(photos);
				accessoryRepository.save(assistiveAccessory);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/assistiveaccessory");
	}

	@GetMapping(value = "/update/{id}")
	public ModelAndView alterarForm(@PathVariable("id") AssistiveAccessory assistiveaccessory) {
		List<Category> categories = categoryRepository.findAll();

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("assistiveaccessory", assistiveaccessory);
		dados.put("categories", categories);
		return new ModelAndView("assistiveaccessory/form", dados);
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {
		this.accessoryRepository.deleteById(id);
		return new ModelAndView("redirect:/assistiveaccessory");
	}
}
