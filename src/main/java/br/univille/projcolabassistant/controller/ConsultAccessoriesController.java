package br.univille.projcolabassistant.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.service.MyUserDetailsService;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/")
public class ConsultAccessoriesController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Value("${accessoryimages.path}")
	private String imgPath;
	
	@GetMapping("")
	public ModelAndView index(HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
		List<AssistiveAccessoryViewModel> listAccessoryViewModel = this.consultAccessoriesRepository.findAllAssistiveAccessoryViewModel();
		
		HashMap<Category, List<AssistiveAccessoryViewModel>> data = new HashMap<Category, List<AssistiveAccessoryViewModel>>();
		
		Category lastCategory = null;
		List<AssistiveAccessoryViewModel> listAssistiveAccessory= null;
		for(AssistiveAccessoryViewModel item : listAccessoryViewModel) {
			if(lastCategory != item.getAssistiveAccessory().getCategory()) {
				lastCategory = item.getAssistiveAccessory().getCategory();
				listAssistiveAccessory= new  ArrayList<AssistiveAccessoryViewModel>();
				
				data.put(lastCategory, listAssistiveAccessory);
			}
			listAssistiveAccessory.add(item);
		}
		HashMap<String, Object> dados = new HashMap<>();
		dados.put("mapcategorylistAccessory", data);
		dados.put("shoppingCart", shoppingCart);
		
				
		return new ModelAndView("catalog/accessoryList", dados);
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
	
	
	
	@GetMapping("/additem/{idaccessory}/{idphoto}")
	public ModelAndView additem(@PathVariable("idaccessory") AssistiveAccessory assistiveAccessory,@PathVariable("idphoto") AccessoryPhoto accessoryPhoto,HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		long sequence = 0;
		for(ItemShoppingCart item:shoppingCart.getItensList()) {
			if(sequence < item.getSequence())
				sequence = item.getSequence();
		}
		
		ItemShoppingCart itemShop = new ItemShoppingCart();
		itemShop.setSequence(sequence+1);
		itemShop.setAccessory(assistiveAccessory);
		itemShop.setPhoto(accessoryPhoto);
		shoppingCart.getItensList().add(itemShop);
		
		
		return new ModelAndView("redirect:/");
	}
	@GetMapping("/admin")
	public ModelAndView admin() {
		
		Collection<? extends GrantedAuthority> colRoles = userDetailsService.getUserRoles();
		
		for(GrantedAuthority item : colRoles) {
			if(item.getAuthority().equals("ROLE_USER"))
				return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("admin/admin");
	}
	@GetMapping("/about")
	public ModelAndView aboutPage() {
		return new ModelAndView("catalog/about");
	}

}