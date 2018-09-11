package br.univille.projcolabassistant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AccessorySize;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;
import br.univille.projcolabassistant.repository.AccessoryPhotoRepository;
import br.univille.projcolabassistant.repository.AccessorySizeRepository;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.valueobject.ItemShoppingCart;
import br.univille.projcolabassistant.valueobject.ShoppingCart;

@Controller
@RequestMapping("/order")

public class OrderController {
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private AssistiveAccessoryRepository assistiveAccessoryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AccessorySizeRepository accessorySizeRepository;
	@Autowired
	private AccessoryColorRepository accessoryColorRepository;
	@Autowired
	private AccessoryPhotoRepository accessoryPhotoRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	public ModelAndView create() {

		ShoppingCart shoppingcart = new ShoppingCart();
		OrderRequest orderrequest = new OrderRequest();

		Category category = new Category();
		category.setName("Categoria 1");
		categoryRepository.save(category);
		
		AccessorySize size = new AccessorySize();
		size.setName("Pequeno");
		accessorySizeRepository.save(size);
		
		AccessoryColor accessoryColor = new AccessoryColor();
		accessoryColor.setName("Branco");
		accessoryColorRepository.save(accessoryColor);
		
		AccessoryPhoto accessoryPhoto = new AccessoryPhoto();
		accessoryPhoto.setDescription("photo");
		accessoryPhotoRepository.save(accessoryPhoto);
		
		City city = new City();
		city.setName("Joinville");
		cityRepository.save(city);
		
		User user = new User();
		user.setCity(city);
		user.setName("user");
		userRepository.save(user);
		
		AssistiveAccessory assistive = new AssistiveAccessory();
		assistive.setCategory(category);
		assistive.getColorList().add(accessoryColor);
		assistive.getSizeList().add(size);
		assistive.getPhotoList().add(accessoryPhoto);
		assistive.setPrincipalPhoto(accessoryPhoto);
		assistiveAccessoryRepository.save(assistive);
		
		ItemShoppingCart item1 = new ItemShoppingCart();
		item1.setAccessory(assistive);
		
		
		
		Institution inst = new Institution();
		inst.setCity(city);
		inst.setName("UNIVILLE");
		institutionRepository.save(inst);

		shoppingcart.getItensList().add(item1);

		for(ItemShoppingCart item : shoppingcart.getItensList()) {
			OrderItems orderitem = new OrderItems();
			orderitem.setQuantity(item.getQuantity());
			orderitem.setAccessory(item.getAccessory());
			orderitem.setAccessoryColor(accessoryColor);
			orderitem.setAccessorySize(size);
			
			orderrequest.getItensList().add(orderitem);
		}
		orderrequest.setUser(user);
		orderrequest.setOrderFinshDate(new Date());
		orderrequest.setOrderDate(new Date());
		orderrequest.setUserRequest(user);
		orderrequest.setInstitution(inst);
		orderRequestRepository.save(orderrequest);
		return new ModelAndView("redirect:/");
	}
	

	@GetMapping("/list")
	public ModelAndView list(@ModelAttribute OrderRequest order) {
		List<OrderRequest> listOrder = orderRequestRepository.findAll();

		return new ModelAndView("order/list", "listorder", listOrder);
	}
}