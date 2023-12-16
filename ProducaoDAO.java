package persistencia;

import java.util.ArrayList;
import java.sql.Date;
import dominio.Producao;

public abstract class ProducaoDAO { //calsse abstrata "Pai" de Jatai e Americana

	public ProducaoDAO() {
		new conexaoDAO("jdbc:postgresql://localhost:5432/BDAGENDA","postgres","123");
	};
	
	public abstract void excluir(Date aux);
	
	public abstract void incluir(Producao pro);
	
	public abstract void alterar(Producao pro);
	
	public abstract Producao buscar(int dAux);
	
	public abstract Producao buscarTipo(int idAux);
	
	public abstract ArrayList<Producao> buscaGeral();
}