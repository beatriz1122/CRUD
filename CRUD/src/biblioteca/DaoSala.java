package biblioteca;

import java.util.List;

public interface DaoSala {
	
	void create(Sala s);
	List<Sala> pesquisarPorNome(String nome);
	
	void delete(Sala s);
	
	void atualizar(String nomeAntigo, Sala s);
	
}
