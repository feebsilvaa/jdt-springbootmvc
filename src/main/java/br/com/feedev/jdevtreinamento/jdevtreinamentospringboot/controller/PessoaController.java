package br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.model.Pessoa;
import br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.service.PessoaService;

@Controller
@RequestMapping(path = "/pessoas")
public class PessoaController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView getPessoas() {
		log.info("Buscando pessoas");
		ModelAndView mav = new ModelAndView("pessoas/lista");
		List<Pessoa> pessoas = this.pessoaService.getPessoas();
		log.info("Pessoas encontradas: {}", pessoas);
		mav.addObject("pessoas", pessoas);
		return mav;
	}
	
	@RequestMapping(path = "busca", method = RequestMethod.POST)
	public ModelAndView getPessoasPorParam(@RequestParam String paramBusca) {
		log.info("Buscando pessoas por parametro: {} ", paramBusca);
		ModelAndView mav = new ModelAndView("pessoas/lista");
		List<Pessoa> pessoas = this.pessoaService.buscarPorParam(paramBusca);
		log.info("Pessoas encontradas: {}", pessoas);
		mav.addObject("pessoas", pessoas);
		return mav;
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ModelAndView savePessoa(Pessoa pessoa) {
		log.info("Salvando pessoa {}", pessoa);
		ModelAndView mav = new ModelAndView("redirect:pessoas");
		this.pessoaService.savePessoa(pessoa);
		return mav;
	}

	@RequestMapping(path = "cadastro", method = RequestMethod.GET)
	public ModelAndView getCadastroPessoa(@ModelAttribute Pessoa pessoa) {
		log.info("Formulario de cadastro de pessoas.");
		ModelAndView mav = new ModelAndView("pessoas/cadastro");
		return mav;
	}
	
	@RequestMapping(path = "editar", method = RequestMethod.GET)
	public ModelAndView getEditaPessoa(@RequestParam Long id) {
		log.info("Editar pessoa com id: {}", id);
		ModelAndView mav = new ModelAndView("pessoas/edicao");
		mav.addObject("pessoa", this.pessoaService.buscarPorId(id).orElse(null));
		return mav;
	}
	
	@RequestMapping(path = "editar", method = RequestMethod.POST)
	public ModelAndView editPessoa(Pessoa pessoa) {
		log.info("Editando pessoa {}", pessoa);
		ModelAndView mav = new ModelAndView("redirect:/pessoas");
		this.pessoaService.editPessoa(pessoa);
		return mav;
	}
	
	@RequestMapping(path = "remover", method = RequestMethod.GET)
	public ModelAndView removerPessoa(@RequestParam Long id) {
		log.info("Removendo pessoa com id: {}", id);
		this.pessoaService.removePessoa(id);
		ModelAndView mav = new ModelAndView("redirect:/pessoas");
		return mav;
	}
	
}
