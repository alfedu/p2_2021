package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.Contact;
import aiss.model.repository.ContactRepository;

public class ContactNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(ContactUpdateController.class.getName());
	
    public ContactNewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Request data
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		// Create contact
		ContactRepository repository = ContactRepository.getInstance();
		repository.addContact(name, phone);

		// Log
		log.log(Level.FINE, "Update request. Name=" + name + ", Telephone= " + phone + ". Forwarding to contact list view.");

		// Forward to contact list view
		request.setAttribute("message", "Contact created successfully");
		request.getRequestDispatcher("/contactlist").forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
