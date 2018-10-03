package br.univille.projcolabassistant.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AccessorySize;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.Material;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;
import br.univille.projcolabassistant.repository.AccessoryPhotoRepository;
import br.univille.projcolabassistant.repository.AccessorySizeRepository;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.MaterialRepository;

@Controller
@RequestMapping("/assistiveaccessory")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AssistiveAccessoryController {
	@Autowired
	private AssistiveAccessoryRepository accessoryRepository;
	@Autowired
	private AccessoryColorRepository accessoryColorRepository;
	@Autowired
	private AccessorySizeRepository accessorySizeRepository;
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AccessoryPhotoRepository accessoryPhotoRepository;
	@Value("${accessoryimages.path}")
	private String imgPath;

	@PostMapping(value = "/new")
	public ModelAndView save(@RequestParam("photos[]") List<MultipartFile> files,  @RequestParam("colorphotos[]") List<AccessoryColor> colorphotos, @RequestParam("desc[]") List<String> descriptions, @Valid AssistiveAccessory assistiveAccessory) {
		try {
			int index = 0;
			List<AccessoryPhoto> photos = new ArrayList<>();
			for (MultipartFile file : files) {
				if(file.getSize() <=0 ) continue;
				AccessoryPhoto accessoryPhoto = new AccessoryPhoto();

				byte[] bytes = file.getBytes(); 

				String imageName = new Timestamp(System.currentTimeMillis()).getTime() + "_" + assistiveAccessory.getId() + "_" + index + ".png";
				Path path = Paths.get(imgPath + imageName);

				accessoryPhoto.setURI(imageName);

				if(!descriptions.isEmpty()) {
					accessoryPhoto.setDescription(descriptions.get(index));
				}
				else {
					accessoryPhoto.setDescription("");
				}
				
				accessoryPhoto.setAccessoryColor(colorphotos.get(index));
				
				if (accessoryPhoto != null) {
					accessoryPhotoRepository.save(accessoryPhoto);
				}
				
				photos.add(accessoryPhoto);

				Files.write(path, bytes);

				index++;
			}

			if (assistiveAccessory != null) {
				assistiveAccessory.getPhotoList().addAll(photos);
				accessoryRepository.save(assistiveAccessory);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/assistiveaccessory");
	}

	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessory> accessoriesList = this.accessoryRepository.findAll();

		return new ModelAndView("assistiveaccessory/index", "assistiveaccessories",accessoriesList);
	}
	@GetMapping("/image-byte-array/{filename}")
	public @ResponseBody byte[] getImageAsByteArray(@PathVariable("filename") String filename) throws IOException {
		try {
		     BufferedImage image = ImageIO.read(new File(String.format("%s/%s", imgPath,filename)));
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     String format = filename.substring(filename.length()-3);
		     ImageIO.write(image, format, baos);
		     return baos.toByteArray();
		   } catch (Exception e) {
		   }
		return null;
	}

	@GetMapping("/new")
	public ModelAndView createForm(@ModelAttribute AssistiveAccessory assistiveaccessory) {
		List<Category> categories = categoryRepository.findAll();
		List<AccessoryColor> colors = accessoryColorRepository.findAll();
		List<AccessorySize> sizes = accessorySizeRepository.findAll();
		List<Material> materials= materialRepository.findAll();


		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("assistiveaccessory", assistiveaccessory);
		dados.put("categories",categories);
		dados.put("colors", colors);
		dados.put("sizes", sizes);
		dados.put("materials", materials);


		return new ModelAndView("assistiveaccessory/form",dados);
	}

	@GetMapping(value="/update/{id}")
	public ModelAndView alterarForm(@PathVariable("id") AssistiveAccessory assistiveaccessory) {
		List<Category> categories = categoryRepository.findAll();
		List<AccessoryColor> colors = accessoryColorRepository.findAll();
		List<AccessorySize> sizes = accessorySizeRepository.findAll();
		List<Material> materials= materialRepository.findAll();

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("assistiveaccessory", assistiveaccessory);
		dados.put("categories",categories);
		dados.put("colors", colors);
		dados.put("sizes", sizes);
		dados.put("materials", materials);

		return new ModelAndView("assistiveaccessory/form",dados);
	}
	
	@GetMapping(value="/removephoto/{idAssistiveAccessory}/{idAccessoryPhoto}")
	public ModelAndView removePhoto(@PathVariable("idAssistiveAccessory") AssistiveAccessory assistiveaccessory,
			@PathVariable("idAccessoryPhoto") AccessoryPhoto accessoryPhoto) {
		List<Category> categories = categoryRepository.findAll();
		List<AccessoryColor> colors = accessoryColorRepository.findAll();
		List<AccessorySize> sizes = accessorySizeRepository.findAll();
		List<Material> materials= materialRepository.findAll();

		assistiveaccessory.getPhotoList().remove(accessoryPhoto);
		
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("assistiveaccessory", assistiveaccessory);
		dados.put("categories",categories);
		dados.put("colors", colors);
		dados.put("sizes", sizes);
		dados.put("materials", materials);

		return new ModelAndView("assistiveaccessory/form",dados);
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		this.accessoryRepository.deleteById(id);
		return new ModelAndView("redirect:/assistiveaccessory");
	}
}
