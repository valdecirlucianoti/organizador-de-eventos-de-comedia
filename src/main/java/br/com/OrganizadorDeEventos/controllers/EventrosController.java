package br.com.OrganizadorDeEventos.controllers;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.OrganizadorDeEventos.models.Convidado;
import br.com.OrganizadorDeEventos.models.Evento;
import br.com.OrganizadorDeEventos.repository.ConvidadoRepository;
import br.com.OrganizadorDeEventos.repository.EventoRepository;

@Controller
public class EventrosController {
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	//Métodos para cadastro de Eventos
		@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
		public String form() {
			return "evento/formEvento";
		}
		
		@RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
		public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
			if(result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos");
				return "redirect:/cadastrarEvento";
			}
			
			System.out.println(evento);
			er.save(evento);
			attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
			return "redirect:/cadastrarEvento";
		}
	
	//lista todos os eventos na index
		@RequestMapping("/listarEventos")
		public ModelAndView listaEventos() {
			ModelAndView view = new ModelAndView("index");
			
			Iterable<Evento> eventos = er.findAll();
			view.addObject("eventos", eventos);
			return view;
		}
		
	//Destalhes do evento
		@RequestMapping(value="/{codigo}", method = RequestMethod.GET)
		public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
			Evento evento = er.findByCodigo(codigo);
			ModelAndView view = new ModelAndView("evento/detalhesEvento");
			view.addObject("evento", evento);
			
			Iterable<Convidado> convidados = cr.findByEvento(evento);
			view.addObject("convidados", convidados);
			return view;
		}
		
	//Salvando os convidados
		@RequestMapping(value="/{codigo}", method = RequestMethod.POST)
		public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
			if(result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos");
				return "redirect:/{codigo}";
			}
			Evento evento = er.findByCodigo(codigo);
			convidado.setEvento(evento);
			cr.save(convidado);
			attributes.addFlashAttribute("mensagem", "Convidado cadastrado com sucesso");
			
			return "redirect:/{codigo}";
		}
}
