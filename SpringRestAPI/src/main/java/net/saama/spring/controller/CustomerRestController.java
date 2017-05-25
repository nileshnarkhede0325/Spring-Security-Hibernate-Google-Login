package net.saama.spring.controller;

import java.util.List;

import net.saama.spring.dao.CustomerDAO;
import net.saama.spring.model.Customer;
import net.saama.spring.service.IAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private IAppServices appServices;

	@GetMapping("/customers")
	public List<?> getCustomers() {
		return customerDAO.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer customer = customerDAO.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id,
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity<Customer> createCustomer(
			@RequestBody Customer customer) {

		customerDAO.create(customer);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/customers/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == customerDAO.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id,
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/customers/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id,
			@RequestBody Customer customer) {

		customer = customerDAO.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id,
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/chkDBConn")
	public ResponseEntity checkHibernateConnection() {
		String str = "Connection done!";
		try {
			appServices.checkConnection();
		} catch (Exception e) {
			e.printStackTrace();
			str = e.getMessage();
		}
		return new ResponseEntity(str, HttpStatus.OK);
	}

}