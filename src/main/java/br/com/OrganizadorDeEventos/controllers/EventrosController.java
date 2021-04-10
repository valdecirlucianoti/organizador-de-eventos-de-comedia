package br.com.OrganizadorDeEventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventrosController {
	@RequestMapping("/cadastrarEvento")
	public String form() {
		return "evento/formEvento";
	}
}
