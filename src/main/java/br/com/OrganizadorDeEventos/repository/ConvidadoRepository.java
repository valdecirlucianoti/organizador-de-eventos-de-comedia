package br.com.OrganizadorDeEventos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.OrganizadorDeEventos.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Convidado findByRg();
}
