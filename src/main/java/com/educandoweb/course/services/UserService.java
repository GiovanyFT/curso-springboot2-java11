package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import com.educandoweb.course.entities.User;

/* Quando colocamos a annotation @Component estamos fazendo o registro de
 um objeto dessa classe no Spring. Isso signifiva que esse objeto será
 instanciado pelo Spring quando eu for utilizá-lo.
 Não precisei fazer o mesmo com UserRepository porque o Spring usa um
 objeto padrão criado por ele para fazer o trabalho, esse objeto inclusive
 vai implementar a interface UserRepository
 
 Para melhorar a semântica ao invés de usar @Component podemos usar @Service
 pois ambos terão o mesmo efeito de criar o objeto, mas o segundo possui nome
 mais relacionado ao que essa classe faz.
 
 Inclusive se fossemos criar uma clase de Repositório utilizaríamos a annotation
 @Repository
*/
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> optional =  repository.findById(id);
		// Vai obter o usuário e caso não exista vai disparar a Exceção personalizada
		return optional.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
