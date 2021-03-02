package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.Contact;
import aiss.model.repository.ContactRepository;

public class ContactUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ContactUpdateController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactUpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Request data
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		if(message == null)
			message="";

		// Contact repository
		ContactRepository repository = ContactRepository.getInstance();

		// Load contact being updated
		Contact contact = repository.getContact(id);

		// Display update form with contact data on it
		if (name == null && phone == null) {

			// Log
			log.log(Level.FINE, "Update request for contact with id " + id + " (name=" + contact.getName() + "). Forwarding to edit view.");

			// Forward to edit view
			RequestDispatcher rd = request.getRequestDispatcher("/contactEditView.jsp");
			request.setAttribute("contact", contact);
			request.setAttribute("message",message);
			rd.forward(request, response);

		}

		// Update request. Update contact and display contact list
		if (name != null && phone != null) {

			// Update contact
			contact.setName(name);
			contact.setTelephone(phone);
			repository.updateContact(contact);

			// Log
			log.log(Level.FINE, "Update request. Name=" + contact.getName() + ", Telephone= " + contact.getTelephone() + ". Forwarding to contact list view.");

			// Forward to contact list view
			request.setAttribute("message", "Contact updated successfully");
			request.getRequestDispatcher("/contactlist").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
