package br.com.OrganizadorDeEventos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.OrganizadorDeEventos.models.Convidado;
import br.com.OrganizadorDeEventos.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
}
