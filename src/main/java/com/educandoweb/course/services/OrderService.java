package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.entities.Order;


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
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> optional =  repository.findById(id);
		return optional.get();
	}
}
