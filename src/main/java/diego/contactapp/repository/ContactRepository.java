package diego.contactapp.repository;

import org.springframework.data.repository.CrudRepository;

import diego.contactapp.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, String>{
	Iterable<Contact> findByDeletedAtIsNull();
	
	Iterable<Contact> findByEmail(String email);
}
