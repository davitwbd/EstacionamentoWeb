package teste;

import java.sql.Connection;
import java.sql.SQLException;

import modeloDAO.Conexao;

public class TesteDeConexaoMySQL {
	
	private static Connection conexaoTeste;

	public static void main(String[] args) throws SQLException {
		
		try{
			conexaoTeste = Conexao.getConnection() ;
			
		}catch(Exception e){
			System.out.println(" Erro de Conexao: "+ e);
			
		}finally{
			conexaoTeste.close();
		}
	}

}
