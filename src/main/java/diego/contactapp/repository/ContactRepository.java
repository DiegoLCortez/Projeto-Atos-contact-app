package diego.contactapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import diego.contactapp.models.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String>{
	Iterable<Contact> findByDeletedAtIsNull();
	
	Iterable<Contact> findByEmail(String email);

	Contact findById(long id);
}
