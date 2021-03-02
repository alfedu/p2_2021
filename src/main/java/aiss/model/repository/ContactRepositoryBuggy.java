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

public class ContactRepositoryBuggy {

	private static final Logger log = Logger.getLogger(ContactRepositoryBuggy.class.getName());
	
	private Map<String,Contact> contacts;
	private static ContactRepositoryBuggy instance=null;
	private int index=0;			// Index to create contacts' identifiers.
	
	public static ContactRepositoryBuggy getInstance() {
		
		if (instance==null) {
			instance = new ContactRepositoryBuggy();
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
			log.log( Level.SEVERE, "Se intentó añadir un contacto que ya existía");
			throw new InvalidParameterException("El contacto ya existe");
		}
		// Create random id
		String id = "c" + index;
		Contact c = new Contact(id, telephone, name); /* BUG. Correct version: Contact c = new Contact(id, name, telephone); */  
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
