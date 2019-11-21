package br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.feedev.jdevtreinamento.jdevtreinamentospringboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p order by p.nome asc")
	List<Pessoa> listarOrdenadoPorNome(); 
	
	@Query( value = ""
			+ "select p "
			+ "from Pessoa p "
			+ "where lower(p.nome) like %:param% "
			+ "or lower(p.sobrenome) like %:param%",
			nativeQuery = false )
	List<Pessoa> buscarPessoaPorParam(@Param(value = "param") String paramBusca);

	List<Pessoa> findByNomeLikeOrSobrenomeLike(String paramBusca, String paramBusca2);

	List<Pessoa> findByNomeLikeIgnoreCaseOrSobrenomeLikeIgnoreCase(String paramBusca, String paramBusca2);

	List<Pessoa> findByNomeContainingOrSobrenomeContaining(String paramBusca,
			String paramBusca2);
}
