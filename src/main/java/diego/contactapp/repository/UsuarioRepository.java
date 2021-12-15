package diego.contactapp.repository;

import org.springframework.data.repository.CrudRepository;

import diego.contactapp.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	Usuario findByLogin(String login);

}
