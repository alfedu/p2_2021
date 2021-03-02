package aiss.model.repository;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import aiss.controller.ContactUpdateController;
import aiss.model.Contact;

public class ContactRepository {

	private static final Logger log = Logger.getLogger(ContactRepository.class.getName());
	
	private Map<String,Contact> contacts;
	private static ContactRepository instance=null;
	private int index=0;			// Index to create contacts' identifiers.
	
	public static ContactRepository getInstance() {
		
		if (instance==null) {
			instance = new ContactRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		contacts = new HashMap<String,Contact>();
		index=0;
		addContact("Manuel Durán", "699045872");
		addContact("Daniel López", "954822754");
		addContact("Ana María Calleja", "362578966");
	}

	public Map<String,Contact> getContacts() {
		return contacts;
	}

	public void updateContact(Contact c) {
		contacts.put(c.getId(), c);
	}

	public Contact getContact(String id) {
		return contacts.get(id);
	}

	public Contact addContact(String name, String telephone) {
		if(findByName(name)!=null){
			log.log( Level.SEVERE, "There already exists a contact with name " + name);
			throw new InvalidParameterException("There already exists a contact with name " + name);
		}
		// Create random id
		String id = "c" + index;
		Contact c = new Contact(id, name, telephone);
		contacts.put(id,c);
		index++;
		return c;
	}

	public void deleteContact(String id) {
		Contact c=contacts.get(id);
		contacts.remove(id);
		log.log(Level.INFO, "El contacto " + c + " fue eliminado");
	}
	
	public Contact findByName(String name){
		Contact result=null;
		for(Contact c:contacts.values()){
			if(c.getName().equals(name)){
				result=c;
				break;
			}
		}
		return result;
	}

}
