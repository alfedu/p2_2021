package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.Contact;
import aiss.model.repository.ContactRepository;

public class ContactListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(ContactListController.class.getName());
	
    public ContactListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Load contacts
		ContactRepository repository = ContactRepository.getInstance();
		Map<String,Contact> contacts = repository.getContacts();
		
		 log.log(Level.FINE, "Processing GET request:  " + contacts.size() + " contacts loaded.");
		
		// Send contacts to index.jsp
		request.setAttribute("contacts", contacts);
		request.getRequestDispatcher("/contactListView.jsp").forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
