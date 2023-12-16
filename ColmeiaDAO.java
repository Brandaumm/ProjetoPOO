package persistencia;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import dominio.Colmeia;


public class ColmeiaDAO implements ControleDAO{

	

	private conexaoDAO c;
	private String INC = "insert into \"Colmeia\" (\"Id_Cm\",\"Tipo_Cm\",\"Satus\") values (?,?,?)";
	private String BUS = "select * from \"Colmeia\" where \"Id_Cm\"=?";
	private String BUSTP = "select * from \"Colmeia\" where \"Tipo_Cm\"=?";
	private String BUSGERAL = "select * from \"Colmeia\"";
	private String DEL = "delete from \"Colmeia\" where \"Id_Cm\" = ? or \"Status\"";
	private String ALT = "update \"Colmeia\" set \"Tipo_Cm\" = ?, \"Status\" = ? where \"Id_Cm\" = ? ";
		
		public ColmeiaDAO() {
			c = new conexaoDAO("jdbc:postgresql://localhost:5432/BDAGENDA","postgres","123");
		}

		
		public void excluir(String status) { //Metodo excluir vindo da interface
			try {
				c.conectar();
				
				PreparedStatement inst = c.getConexao().prepareStatement(DEL);
				inst.setString(1, status);
				inst.execute();
				c.desconectar();
				
			}catch(Exception e) {
				System.out.println("Erro na exclusao" + "getminhaconexao");
			}
		}
		
		
		public void excluir(String status, int id) {//Sobrecarga do metodo excluir vindo da interface
			try {
				c.conectar();
				
				PreparedStatement inst = c.getConexao().prepareStatement(DEL);
				inst.setString(1, status);
				inst.setString(1, status);
				inst.execute();
				c.desconectar();
				
			}catch(Exception e) {
				System.out.println("Erro na exclusao" + "getminhaconexao");
			}
		}
		
		
		public void incluir(Colmeia cm) {// Incluir uma nova Colmeia, caso nao exista
			try {
				
				c.conectar();
				PreparedStatement inst = c.getConexao().prepareStatement(INC);
				inst.setInt(1, cm.getId());
				inst.setInt(2, cm.getTipo());
				inst.setString(3, cm.getStatus());
				inst.execute();
				c.desconectar();
				
			}catch(Exception e) {
				System.out.println("Erro na inclusao" + "getminhaconexao");
			}
		}
		
		
		public void alterar(Colmeia cm) { // Alterar/Atualizar, caso ela exista
			try {
				
				c.conectar();
				PreparedStatement inst = c.getConexao().prepareStatement(ALT);
				inst.setInt(1, cm.getId());
				inst.setInt(2, cm.getTipo());
				inst.setString(3, cm.getStatus());
				inst.execute();
				c.desconectar();
				
			}catch(Exception e) {
				System.out.println("Erro na alteracao"+ "getminhaconexao");
			}
		} 
		
		
		public Colmeia buscar(int idAux) {//Buscar Colmeia pelo id int. Se ele existir (alterar ou deletar) se nao (incluir)
			Colmeia cm = null;
			try {
				
				c.conectar();
				PreparedStatement inst = c.getConexao().prepareStatement(BUS);
				inst.setInt(1,idAux);
				ResultSet rs = inst.executeQuery();
				if(rs.next()) { 
					cm = new Colmeia(rs.getInt("id"), rs.getInt("tipo"), rs.getString("status"));
				}
				
				c.desconectar();
			}catch(Exception e) {
				System.out.println("Erro na busca"+ "getminhaconexao");
			}
			return cm;
		}
		
		public ArrayList<Colmeia> buscaTipo() { //busca todas as colmeias por tipo
			Colmeia cm;
			ArrayList<Colmeia> listaTp = new ArrayList<Colmeia>();
			try {
				c.conectar();
				Statement inst = c.getConexao().createStatement(); 
				ResultSet rs = inst.executeQuery(BUSTP);
				while(rs.next()) { 
					cm = new Colmeia(rs.getInt("id"), rs.getInt("tipo"), rs.getString("status"));
					listaTp.add(cm);
				}
				c.desconectar();
			}catch(Exception e) {
				System.out.println("Erro na busca");
			}
			return listaTp;
		}
		
		public ArrayList<Colmeia> buscaGeral() { //Relatoria de todas as Colmeias
			Colmeia cm;
			ArrayList<Colmeia> lista = new ArrayList<Colmeia>();
			try {
				c.conectar();
				Statement inst = c.getConexao().createStatement(); 
				ResultSet rs = inst.executeQuery(BUSGERAL);
				while(rs.next()) { 
					cm = new Colmeia(rs.getInt("id"), rs.getInt("tipo"), rs.getString("status"));
					lista.add(cm);
				}
				c.desconectar();
			}catch(Exception e) {
				System.out.println("Erro na busca");
			}
			return lista;
		}
}