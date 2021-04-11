package br.com.OrganizadorDeEventos.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.OrganizadorDeEventos.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findByCodigo(long codigo);
}
