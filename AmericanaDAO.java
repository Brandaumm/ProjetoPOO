package persistencia;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import dominio.Producao;
import java.sql.Date;

public class AmericanaDAO  extends ProducaoDAO { //Herança da classe Pai
	
	private conexaoDAO c;
	private String INC = "insert into \"Producao\" (\"quantidade\",\"tipo\",\"dia\") values (?,?,?,)";
	private String BUS = "select * from \"Producao\" where \"ID\"=?";
	private String BUSTP = "select * from \"Producao\" where \"tipo\"=?";
	private String BUSGERAL = "select * from \"Producao\"";
	private String DEL = "delete from \"Producao\" where \"dia\" = ?";
	private String ALT = "update \"Producao\" set \"quantidade\" = ?, \"tipo\" = ? where \"dia\" = ? ";	
	
	public AmericanaDAO() {
		c = new conexaoDAO("jdbc:postgresql://localhost:5432/BDAGENDA","postgres","123");
	}
	

	public void excluir(Date aux) { // Exclusao por data (date vindo do SQL), a exclusao so vai ocorrer caso a data exista no banco
		try {
			c.conectar();
			
			PreparedStatement inst = c.getConexao().prepareStatement(DEL);
			inst.setDate(1, aux);
			inst.execute();
			c.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na exclusao" + "getminhaconexao");
		}
	};
	
	public void incluir(Producao pro) { // Incluir uma producao, mas so pode incluir se informar o tipo

		try {
		c.conectar();
		PreparedStatement inst = c.getConexao().prepareStatement(INC);
		inst.setFloat(1, pro.getProducaoT());
		inst.setInt(2, pro.getTipo());
		inst.setDate(3, pro.getDia());
		inst.execute();
		c.desconectar();
		
		}catch(Exception e) {
		System.out.println("Erro na inclusao");
		}
	};
	
	public void alterar(Producao pro) { // Aleterar apenas se a protucao ja existir
		try {
			
			c.conectar();
			PreparedStatement inst = c.getConexao().prepareStatement(ALT);
			inst.setFloat(1, pro.getProducaoT());
			inst.setInt(2, pro.getTipo());
			inst.setDate(3, pro.getDia());
			inst.execute();
			c.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na alteracao"+ "getminhaconexao");
		}
	};
	
	public Producao buscar(int ID) { // Buscar uma producao ja existente e deve informar o tipo  
	
		Producao pro = null;
		try {
			
			c.conectar();
			PreparedStatement inst = c.getConexao().prepareStatement(BUS);
			inst.setInt(1, ID);
			ResultSet rs = inst.executeQuery();
			if(rs.next()) { 
				pro = new Producao (rs.getFloat("quantidade"), rs.getInt("tipo"), rs.getDate("dia"), rs.getInt("ID"));
			}
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca"+ "getminhaconexao");
		}
		return pro;
		
	};
	
	
	
	public Producao buscarTipo(int idAux) { // Relatorio geral com filtro e de tipo
		
		Producao pro = null;
		try {
			
			c.conectar();
			PreparedStatement inst = c.getConexao().prepareStatement(BUSTP);
			inst.setInt(1,idAux);
			ResultSet rs = inst.executeQuery();
			if(rs.next()) { 
				pro = new Producao (rs.getFloat("quantidade"), rs.getInt("tipo"), rs.getDate("dia"), rs.getInt("ID"));
			}
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca"+ "getminhaconexao");
		}
		return pro;
	};
	
	public ArrayList<Producao> buscaGeral(){ // Relatorio Geral
		
		Producao pro;
		ArrayList<Producao> lista = new ArrayList<Producao>();
		try {
			c.conectar();
			Statement inst = c.getConexao().createStatement(); 
			ResultSet rs = inst.executeQuery(BUSGERAL);
			while(rs.next()) { 
				pro = new Producao (rs.getFloat("quantidade"), rs.getInt("tipo"), rs.getDate("dia"), rs.getInt("ID"));
				lista.add(pro);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca"+ "getminhaconexao");
		}
		return lista;
	}


	
}