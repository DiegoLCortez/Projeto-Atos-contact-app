package diego.contactapp.controllers;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import diego.contactapp.models.Contact;
import diego.contactapp.repository.ContactRepository;

@Controller
public class ContactController {

	@Autowired
	private ContactRepository cr;
	
	@RequestMapping(value="/contactform", method = RequestMethod.GET)
	public String form() {
		return "contact/formContact";
	}
	
	@RequestMapping(value="/contactform", method = RequestMethod.POST)
	public String form(@Valid Contact contact, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagemErro", "Preencha os campos!");
			return "redirect:/contactform";
		}
		cr.save(contact);
		attributes.addFlashAttribute("mensagemSucesso", "Solicitação realizada com sucesso!");
		return "redirect:/contactform";
	}
	
	@RequestMapping("/contacts")
	public ModelAndView contactList() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Contact> contacts = cr.findByDeletedAtIsNull();
		mv.addObject("contacts", contacts);
		
		return mv;
	}
	
	@RequestMapping("/{email}")
	public ModelAndView historicoContact(@PathVariable("email") String email) {
		Iterable<Contact> contacts = cr.findByEmail(email);
		ModelAndView mv = new ModelAndView("contact/contactHistory");
		mv.addObject("contacts", contacts);
		
		return mv;
	}
	
	@RequestMapping("/contacts-all")
	public ModelAndView contactListAll() {
		ModelAndView mv = new ModelAndView("contact/contactAll");
		Iterable<Contact> contacts = cr.findAll();
		mv.addObject("contacts", contacts);
		
		return mv;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteAt(@PathVariable("id") long id) {
		Contact c = cr.findById(id);
		c.setDeletedAt(new Timestamp(System.currentTimeMillis()));
		cr.save(c);
		
		return "redirect:/contacts";
	}
	
}
