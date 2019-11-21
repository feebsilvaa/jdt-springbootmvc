package br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.model.Pessoa;
import br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.repository.PessoaRepository;

@Service
public class PessoaService {

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> getPessoas() {
		log.info("Listando pessoas.");
		return this.pessoaRepository.listarOrdenadoPorNome();
	}

	public Optional<Pessoa> savePessoa(@Valid Pessoa pessoa) {
		log.info("Salvando pessoa: {}", pessoa);
		return Optional.of(this.pessoaRepository.save(pessoa));
	}

	public Optional<Pessoa> buscarPorId(Long id) {
		log.info("Buscando pessoa por id: {}", id);
		return this.pessoaRepository.findById(id);
	}

	public Optional<Pessoa> editPessoa(Pessoa pessoa) {
		log.info("Editando pessoa: {}", pessoa);
		return Optional.of(this.pessoaRepository.saveAndFlush(pessoa));
		
	}

	public void removePessoa(Long id) {
		log.info("Removendo pessoa com id: {}", id);
		this.pessoaRepository.deleteById(id);
	}

	public List<Pessoa> buscarPorParam(String paramBusca) {
		log.info("Buscando pessoas com parametro: {}", paramBusca);
		List<Pessoa> pessoas = null;
		if (paramBusca != null && !paramBusca.isEmpty()) {
			pessoas = this.pessoaRepository.buscarPessoaPorParam(paramBusca);			
		} else {
			pessoas = this.getPessoas();			
		}
		log.info("Pessoas encontradas: {}", pessoas);
		return pessoas;
	}

}
