package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import persistencia.funcionarioDAO;
import persistencia.ColmeiaDAO;
import persistencia.ProducaoDAO;
import persistencia.JataiDAO;
import persistencia.AmericanaDAO;

import dominio.Americana;
import dominio.Colmeia;
import dominio.Funcionario;
import dominio.Jatai;
import dominio.Producao;

import java.util.Date;

public class ProjetoPOOController implements Initializable {
	@FXML
	private AnchorPane login, funcionarios,colmeias,producao;
	@FXML
	//botao funcionario e login
	private Button btnOK,btnAlterarFuncioario,btnBuscaGeralFuncionario,
	btnExcluirFuncionario,btnIncluirFuncionario,btnBuscarFuncionario;
	@FXML
	//botao colmeia
	private Button btnBuscarColmeia,btnIncluirColmeia,btnAlterarColmeia,
	btnBuscaGeralColmeia,btnExcluirColmeia;
	
	@FXML
	//botao producao
	private Button btnProducaoTotal, btnAbelhaJatai, btnAbelhaAmericana,
	btnInserirProducaoJatai,inserirProducaoAmericana,btnBuscarProducaoAmericana,btnBuscarProducaoJatai
	,btnAlterarProducaoJatai,btnAlterarProducaoAmericana,btnProducaoAmericana,
	btnProducaoJatai;
	
	@FXML
	//textField funcionarios e colmeia
	private TextField nomeFuncionario,matriculaFuncionario, emailFuncionario, IDColmeia
	,tipoColmeia,statusColmeia;	
	
	@FXML
	//textField producao
	private TextField ProducaoProducao,diaProducao;
	
	@FXML
	//todas as labels
	private Label avisoTexto, teste, avisoTextoColmeia, avisoTextoProducao;
	
	 @FXML
	    private void initialize() {
	        matriculaFuncionario.textProperty().addListener((observable, oldValue, newValue) -> {
	            btnIncluirFuncionario.setDisable(newValue.isEmpty());
	        });
	    }
	
	
	// Event Listener on Button.onAction
	@FXML
	public void btnOKClicked(ActionEvent event) {
	login.setVisible(false);
	funcionarios.setVisible(true);
	colmeias.setVisible(false);
	producao.setVisible(false);
	}
	@FXML
	public void btnFuncionarioClicked(ActionEvent event) {
		login.setVisible(false);
		funcionarios.setVisible(true);
		colmeias.setVisible(false);
		producao.setVisible(false);
		}
	@FXML
	public void btnColmeiaClicked(ActionEvent event) {
		login.setVisible(false);
		funcionarios.setVisible(false);
		colmeias.setVisible(true);
		producao.setVisible(false);
		}
	@FXML
	public void btnProducaoClicked(ActionEvent event) {
		login.setVisible(false);
		funcionarios.setVisible(false);
		colmeias.setVisible(false);
		producao.setVisible(true);
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		login.setVisible(true);
		colmeias.setVisible(false);
		funcionarios.setVisible(false);
		producao.setVisible(false);
	}
	
	@FXML
	public void btnIncluirFuncionarioClicked(ActionEvent event) {
		funcionarioDAO inclusao = new funcionarioDAO();
		Funcionario funcionario = new Funcionario();
		String matriculaTexto = matriculaFuncionario.getText();
		
		if(nomeFuncionario.getText().isEmpty() || matriculaFuncionario.getText().isEmpty() || emailFuncionario.getText().isEmpty()) {
			avisoTexto.setText("Insira os campos corretamente");
		}else if(!matriculaTexto.matches("\\d+")) {
			avisoTexto.setText("A area de matricula so pode conter numeros");
		}
		else {
			
			
			
			funcionario.setMatricula(matriculaFuncionario.getText());
			funcionario.setNome(nomeFuncionario.getText());
			funcionario.setEmail(emailFuncionario.getText());
			
			
			
			avisoTexto.setText("Funcionario adcionado");
			inclusao.incluir(funcionario);
			
		}
		
	}
	@FXML
	public void btnExcluirFuncionario(ActionEvent event) {
	    funcionarioDAO excluir = new funcionarioDAO();
	    String conteudo = matriculaFuncionario.getText();

	    if (matriculaFuncionario.getText().trim().isEmpty()) {
	        avisoTexto.setText("Insira uma matrícula para realizar a exclusão");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTexto.setText("A área de matrícula só pode conter números.");
	    } else {
	        try {
	            
	            avisoTexto.setText("Funcionario excluido");
	            excluir.excluir(matriculaFuncionario.getText());
	            matriculaFuncionario.setText("");
	        } catch (NumberFormatException e) {
	            avisoTexto.setText("Impossivel excluir esta matricula");
	            matriculaFuncionario.setText("");
	        }
	    }
	}
	
	@FXML
	public void btnBuscaGeralFuncionario(ActionEvent event) {
		try {
		funcionarioDAO buscaGeral = new funcionarioDAO();

		buscaGeral.buscaGeral();
		}catch(NumberFormatException e) {
			avisoTexto.setText("Não foi possivel realizar a busca geral");
			
		}
		
	}
	@FXML
	public void btnBuscarFuncionario(ActionEvent event) {
		funcionarioDAO buscar = new funcionarioDAO();
		
		String conteudo = matriculaFuncionario.getText();
		 if (matriculaFuncionario.getText().trim().isEmpty()) {
		        avisoTexto.setText("Insira uma matrícula para realizar a busca");
		    } else if (!conteudo.matches("\\d+")) {
		        avisoTexto.setText("A área de matrícula só pode conter números.");
		    } else {
		        try {
		        	avisoTexto.setText("Busca realizada com sucesso");
		        	matriculaFuncionario.setText("");
		            buscar.buscar(matriculaFuncionario.getText());
		        } catch (NumberFormatException e) {
		            avisoTexto.setText("Não foi possivel realizar a busca desta matricula");
		            matriculaFuncionario.setText("");
		        }
		    }
		
		    }
	
	public void btnAlterarFuncionario(ActionEvent event) {
		funcionarioDAO alterarFuncionario = new funcionarioDAO();
		Funcionario funcionario = new Funcionario();
		String conteudo = matriculaFuncionario.getText();
		if (matriculaFuncionario.getText().trim().isEmpty()) {
	        avisoTexto.setText("Insira uma matrícula para realizar a busca");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTexto.setText("A área de matrícula só pode conter números.");
	    }	else if( nomeFuncionario.getText().isEmpty() || matriculaFuncionario.getText().isEmpty() || emailFuncionario.getText().isEmpty()) {
	    	avisoTexto.setText("Insira os campos corretamente");
	    }else {
	    	try {
		    funcionario.setEmail(emailFuncionario.getText());
	    	funcionario.setMatricula(matriculaFuncionario.getText());
	    	funcionario.setNome(nomeFuncionario.getText());
	    	alterarFuncionario.alterar(funcionario);
	    	avisoTexto.setText("Funcionario Alterado");
	    	
	    	nomeFuncionario.setText("");
			matriculaFuncionario.setText("");
			emailFuncionario.setText("");

	    	}catch(NumberFormatException e) {
	    		avisoTexto.setText("Não foi possivel alterar este ID");
	    		nomeFuncionario.setText("");
				matriculaFuncionario.setText("");
				emailFuncionario.setText("");
		
	    	}
	    }
	}
	
	
	// COLMEIA
	
	
	
	public void btnBuscarColmeia(ActionEvent event) {
		ColmeiaDAO buscarColmeia = new ColmeiaDAO();
		
		String conteudo = IDColmeia.getText();
		
		if (IDColmeia.getText().trim().isEmpty()) {
	        avisoTextoColmeia.setText("Insira um ID para realizar a busca");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTexto.setText("A área de ID só pode conter números.");
	    }else if(IDColmeia.getText().isEmpty()) {
	    	avisoTextoColmeia.setText("Insira os campos corretamente");
	    }else {
	    	try {
	    	
	    	int numero = Integer.parseInt(conteudo);
	    	buscarColmeia.buscar(numero);
	    	avisoTextoColmeia.setText("Realizando busca");
	    }catch(NumberFormatException e ) {
	    	avisoTextoColmeia.setText("Não foi possivel realizar a busca por este ID");
	    	IDColmeia.setText("");
		
	    }
	    }
		
	}
	
	public void btnIncluirColmeia(ActionEvent event) {
		ColmeiaDAO incluirColmeia = new ColmeiaDAO();
		Colmeia colmeia = new Colmeia();
		
		String conteudo = IDColmeia.getText();
		String tipo = tipoColmeia.getText();
		int tipoNumero = Integer.parseInt(tipo);
		int numero = Integer.parseInt(conteudo);
		
		if (IDColmeia.getText().trim().isEmpty()) {
	        avisoTextoColmeia.setText("Insira um ID para realizar a busca");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTextoColmeia.setText("A área de ID só pode conter números.");
	    }else if(IDColmeia.getText().isEmpty() || tipoColmeia.getText().isEmpty()) {
	    	avisoTextoColmeia.setText("Insira os campos corretamente");
	    }else if (tipoNumero > 2 || tipoNumero < 1) {
	    	avisoTextoColmeia.setText("O tipo da colmeia so pode ser 1 ou 2");
	    }else {
	    	try {
	    	colmeia.setId(numero);
	    	colmeia.setStatus(statusColmeia.getText());
	    	colmeia.setTipo(tipoNumero);
	    	incluirColmeia.incluir(colmeia);
	    	
	    	IDColmeia.setText("");
	    	statusColmeia.setText("");
	    	tipoColmeia.setText("");
	    	avisoTextoColmeia.setText("Inclusão de colmeia realizada com sucesso");
	    }catch(NumberFormatException e) {
	    		avisoTexto.setText("Não foi possivel incluir a colmeia");
	    		IDColmeia.setText("");
	    		tipoColmeia.setText("");
	    		statusColmeia.setText("");
	    }
	    }
		
	}
	
	@FXML
	public void btnAlterarColmeia(ActionEvent event) {
		ColmeiaDAO alterarColmeia = new ColmeiaDAO();
		Colmeia colmeia = new Colmeia();
		
		String conteudo = IDColmeia.getText();
		String tipo = tipoColmeia.getText();
		int tipoNumero = Integer.parseInt(tipo);
		int numero = Integer.parseInt(conteudo);
		
		if (IDColmeia.getText().trim().isEmpty()) {
	        avisoTextoColmeia.setText("Insira um ID para realizar a busca");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTextoColmeia.setText("A área de ID só pode conter números.");
	    }else if(IDColmeia.getText().isEmpty() || tipoColmeia.getText().isEmpty()) {
	    	avisoTextoColmeia.setText("Insira os campos corretamente");
	    }else if (tipoNumero > 2 || tipoNumero < 1) {
	    	avisoTextoColmeia.setText("O tipo da colmeia so pode ser 1 ou 2");
	    }else {
	    	try {
	    	colmeia.setId(numero);
	    	colmeia.setTipo(tipoNumero);
	    	colmeia.setStatus(statusColmeia.getText());
	    	alterarColmeia.alterar(colmeia);
	    	IDColmeia.setText("");
	    	tipoColmeia.setText("");
	    	statusColmeia.setText("");
	    	avisoTextoColmeia.setText("Alteração realizada com sucesso");
	    }catch(NumberFormatException e ){
	    	avisoTextoColmeia.setText("Não foi possivel alterar este ID");
	    	IDColmeia.setText("");
    		tipoColmeia.setText("");
    		statusColmeia.setText("");
	    }
	    	
	    }
		
	}
	
	public void btnBuscaGeralColmeia(ActionEvent event) {
		try {
		ColmeiaDAO buscaGeral = new ColmeiaDAO();
		buscaGeral.buscaGeral();
		avisoTextoColmeia.setText("Realizando a busca geral");
		}catch(NumberFormatException e) {
			avisoTextoColmeia.setText("Não foi possivel realizar a busca geral");
		}
	}
	
	public void btnExcluirColmeia(ActionEvent event) {
		ColmeiaDAO excluirColmeia = new ColmeiaDAO();
		
		String conteudo = IDColmeia.getText();
		
		if (IDColmeia.getText().trim().isEmpty()) {
	        avisoTextoColmeia.setText("Insira um ID para realizar a busca");
	    } else if (!conteudo.matches("\\d+")) {
	        avisoTextoColmeia.setText("A área de ID só pode conter números.");
	    }else if(IDColmeia.getText().isEmpty()) {
	    	avisoTextoColmeia.setText("Insira os campos corretamente");
	    }else if (statusColmeia.getText().isEmpty()){
	    	
	    	excluirColmeia.excluir(statusColmeia.getText());
	    }else {
	    	try {
	    		
	    	int numero = Integer.parseInt(conteudo);
	    	
	    	excluirColmeia.excluir(statusColmeia.getText(), numero);
	    	avisoTextoColmeia.setText("Exclusão realizada com sucesso");
	    	IDColmeia.setText("");
	    }catch(NumberFormatException e) {
	    	avisoTextoColmeia.setText("Não foi possivel excluir este ID");
	    	IDColmeia.setText("");
	    }
	    }
	}
	
	
	// PRODUÇÃO
	
	public void btnProducaoJatai(ActionEvent event) {
		JataiDAO inserirJatai = new JataiDAO();
		Jatai jatai = new Jatai();
		int tipo = 1;
		Producao producao = new Producao();
		
		String conteudo = diaProducao.getText();
		String Producao = ProducaoProducao.getText();
		
		if (diaProducao.getText().trim().isEmpty()) {
	        avisoTextoProducao.setText("Insira um dia para realizar a função.");
	    } else if (verificarFormatoData(diaProducao.getText())) {
	        avisoTextoProducao.setText("Insira uma data valida");
	    }else if (!Producao.matches("\\d+")){
	    	avisoTextoProducao.setText("A área de produção só pode conter números.");
	    }else if(diaProducao.getText().isEmpty() || ProducaoProducao.getText().isEmpty()) {
	    	avisoTextoProducao.setText("Insira os campos corretamente");
	    }else {
	    	try {
	    	int dia = Integer.parseInt(conteudo);
	    	
	    	String formato = "yyyy-MM-dd";
	    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    	sdf.setLenient(false);
	    	
	    	java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(sdf.parse(diaProducao.getText()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	float qntProducao = Float.parseFloat(Producao);
			
	    	jatai.setDia(sqlDate);
	    	jatai.setProducaoJ(qntProducao);
	    	jatai.setTipo(tipo);
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    	
	    	inserirJatai.incluir(jatai);
	    	avisoTextoColmeia.setText("Inserção de produção Jatai realizada com sucesso ");
	    }catch(NumberFormatException e) {
	    	avisoTextoColmeia.setText("Não foi possivel inserir a produção de Jatai");
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    	
	    }
	    }
	}
	
	public void btnProducaoAmericana(ActionEvent event) {
		AmericanaDAO inserirAmericana = new AmericanaDAO();
		int tipo = 2;
		Americana americana = new Americana();
		String conteudo = diaProducao.getText();
		String Producao = ProducaoProducao.getText();
		
		if (diaProducao.getText().trim().isEmpty()) {
	        avisoTextoProducao.setText("Insira um dia para realizar a função.");
	    } else if (verificarFormatoData(diaProducao.getText())) {
	        avisoTextoProducao.setText("Insira uma data valida");
	    }else if (!Producao.matches("\\d+")){
	    	avisoTextoProducao.setText("A área de produção só pode conter números.");
	    }else if(diaProducao.getText().isEmpty() || ProducaoProducao.getText().isEmpty()) {
	    	avisoTextoProducao.setText("Insira os campos corretamente");
	    }else {
	    	try {
	    	String formato = "dd-MM-yyyy";
	    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    	sdf.setLenient(false);
	    	
	    	java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(sdf.parse(diaProducao.getText()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	int quantidade = Integer.parseInt(Producao);
	    	
	    	
	    	int dia = Integer.parseInt(conteudo);
	    	float producao = Float.parseFloat(Producao);
	    	americana.setDia(sqlDate); 
	    	americana.setTipo(tipo);
	    	americana.setProducaoA(producao);
	    	inserirAmericana.incluir(americana);
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    	avisoTextoProducao.setText("Inserção de produção americana realizada com sucesso");
	    }catch(NumberFormatException e) {
	    	avisoTextoProducao.setText("Não foi possivel inserir a produção de Americana");
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    }
	    }
	    	 
	    
	    
	}
	
	public void btnAlterarProducaoAmericana(ActionEvent event) {
		AmericanaDAO alterarAmericana = new AmericanaDAO();
		int tipo = 2;
		Producao alterar = new Producao();
		
		String conteudo = diaProducao.getText();
		String Producao = ProducaoProducao.getText();
		
		if (diaProducao.getText().trim().isEmpty()) {
	        avisoTextoProducao.setText("Insira um dia para realizar a função.");
	    } else if (verificarFormatoData(diaProducao.getText())) {
	        avisoTextoProducao.setText("Insira uma data valida");
	    }else if (!Producao.matches("\\d+")){
	    	avisoTextoProducao.setText("A área de produção só pode conter números.");
	    }else if(diaProducao.getText().isEmpty() || ProducaoProducao.getText().isEmpty()) {
	    	avisoTextoProducao.setText("Insira os campos corretamente");
	    }else {
	    	try {
	    	int dia = Integer.parseInt(conteudo);
	    	int producao = Integer.parseInt(Producao);

	    	String formato = "dd-MM-yyyy";
	    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    	sdf.setLenient(false);
	    	
	    	java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(sdf.parse(diaProducao.getText()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	alterar.setDia(sqlDate);
	    	alterar.setProducaoT(producao);
	    	alterar.setTipo(tipo);
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    	
	    	avisoTextoProducao.setText("Produção Americana Alterada com sucesso");
	    	alterarAmericana.alterar(alterar);
	    
	    }catch(NumberFormatException e){
	    	avisoTextoProducao.setText("Não foi possivel alterar a produção de Americanas");
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    }
	    }
		}
	
	
	public void btnAlterarProducaoJatai(ActionEvent event) {
		JataiDAO alterarJatai = new JataiDAO();
		int tipo =1;
		
		Jatai jatai = new Jatai();
		
		String conteudo = diaProducao.getText();
		String Producao = ProducaoProducao.getText();
		
		if (diaProducao.getText().trim().isEmpty()) {
	        avisoTextoProducao.setText("Insira um dia para realizar a função.");
	    } else if (verificarFormatoData(diaProducao.getText())) {
	        avisoTextoProducao.setText("Insira uma data valida");
	    }else if (!Producao.matches("\\d+")){
	    	avisoTextoProducao.setText("A área de produção só pode conter números.");
	    }else if(diaProducao.getText().isEmpty() || ProducaoProducao.getText().isEmpty()) {
	    	avisoTextoProducao.setText("Insira os campos corretamente");
	    }else {
	    	try {
	    	int dia = Integer.parseInt(conteudo);
	    	int producao = Integer.parseInt(Producao);
	    	
	    	String formato = "yyyy-MM-dd";
	    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    	sdf.setLenient(false);
	    	
	    	java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(sdf.parse(diaProducao.getText()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jatai.setDia(sqlDate);
			jatai.setTipo(tipo);
			jatai.setProducaoJ(producao);
			
	    	
	    	alterarJatai.alterar(jatai);
	    	diaProducao.setText("");
	    	ProducaoProducao.setText("");
	    	}catch(NumberFormatException e) {
	    		avisoTextoProducao.setText("Não foi possivel alterar a produção Jatai");
	    		diaProducao.setText("");
		    	ProducaoProducao.setText("");
	    	}
	    }
	}
	
	
	public void btnProducaoTotal(ActionEvent event) {
		
	}
	
	public void btnAbelhaJatai(ActionEvent event) {
		JataiDAO abelhaJatai = new JataiDAO();
		abelhaJatai.buscaGeral();
	}
	
	public void btnAbelhaAmericana(ActionEvent event) {
		AmericanaDAO abelhaAmericana = new AmericanaDAO();
		abelhaAmericana.buscaGeral();
	}
	

	
	public void btnBuscarProducaoJatai(ActionEvent event) {
		
	}
	
	
	public void btnBuscarProducaoAmericana(ActionEvent event) {
		
	}
	
	
	private boolean verificarFormatoData(String texto) {
		String regex = "\\d{2}/\\d{2}/\\d{4}";
		return texto.matches(regex);
	}
	

}
