package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import modelo.Carro;

public class CarroDAO {
	
	private Connection conexao = (Connection) Conexao.getConnection();

	
    public void salvar (Carro carro){
    	
    	String sql = "INSERT INTO carro (placa,cor,modelo) VALUES(?, ? , ?)";
    	try {
    		//Preparando SQL
    		PreparedStatement preparador = conexao.prepareStatement(sql);
    		
    		//Substituindo os parametros do String SQL pelos valores do objeto carro
    		preparador.setString(1, carro.getPlaca());
    		preparador.setString(2, carro.getCor());
    		preparador.setString(3, carro.getModelo());
    		
    		//Executa SQL
    		preparador.execute();
    		
    		//Fecha Statment
    		preparador.close();
    		System.out.println("Carro salvo com sucesso!");
    	} 
    	catch (SQLException e) 
    	{
    		System.out.println("Erro de SQL:"+ e.getMessage());
    	}
	}

	public void excluir (Carro carro){
		
		String sql = "delete from carro where id=?";
    	try {
    		PreparedStatement preparador = conexao.prepareStatement(sql);
    		preparador.setInt(1, carro.getId());
    		
    		//Executa SQL
    		preparador.execute();
    		
    		//Fecha Statment
    		preparador.close();
    		System.out.println("Carro Excluído com sucesso!");
    	} catch (SQLException e) {
    		System.out.println("Erro de SQL:"+ e.getMessage());
    	}
	}

	public void atualizar (Carro carro){
		
		String sql = "update carro set placa=?, cor=?, modelo=? where id=?";
    	try {
    		PreparedStatement preparador = conexao.prepareStatement(sql);
    		preparador.setString(1, carro.getPlaca());
    		preparador.setString(2, carro.getCor());
    		preparador.setString(3, carro.getModelo());
    		preparador.setInt(4, carro.getId());
    		
    		//Executa SQL
    		preparador.execute();
    		
    		//Fecha Statment
    		preparador.close();
    		System.out.println("Carro Atualizado com sucesso");
    		
    	} catch (SQLException e) {
    		System.out.println("Erro de SQL:"+ e.getMessage());
    	}
	}
	
	public ArrayList<Carro> listarTodos (){
		//Lista de retorno do método
		ArrayList<Carro> listaDosCarrosEstacionados = new ArrayList<Carro>();
		
		//Prepara o SQL
    	String sql = "select * from carro order by id";
    	try {
    		PreparedStatement preparador = conexao.prepareStatement(sql);
    		
    		//ResultSet será usado para armazenar o retorno da consulta.
    		ResultSet resultado;
    		
    		//Executa a consulta e armazena os dados no resultado.
    		resultado = preparador.executeQuery();
    		
    		//While usado para Navegada nos registros do resultado.
    		while(resultado.next()){
    			
    			//em cada loop >> instancia um novo objeto carro.
    			Carro carro = new Carro();
    			
    			//Carega o carro como os dados
    			carro.setId(resultado.getInt("id"));
    			carro.setPlaca(resultado.getString("placa"));
    			carro.setCor(resultado.getString("cor"));
    			carro.setModelo(resultado.getString("modelo"));
    			
    			//adiciona na lista
    			listaDosCarrosEstacionados.add(carro);
    		}
    		System.out.println("Carros Listados com sucesso!");
    	} catch (SQLException e) {
    		System.out.println("Erro de SQL:"+ e.getMessage());
    	}
		
		return listaDosCarrosEstacionados;
	}
}
