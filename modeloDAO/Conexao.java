package modeloDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

	final static private String driver = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		Connection conexao = null;
		try {
			
			Class.forName(driver);//Indica e carregar o driver utilizado
			
			          //DriverManager.getConnection("baseDeDados", "user", "password")
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/bd_estacionamento", "root", "senha");
			System.out.println("Conectou com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Nao conectou no Banco:" + e.getMessage());

			JOptionPane.showMessageDialog(null, "Erro de Conexao: \n" + e.getMessage(), "Teste Conexao",
					JOptionPane.ERROR_MESSAGE);
			
		} catch (ClassNotFoundException Driver) {			
			JOptionPane.showMessageDialog(null, "Driver nao localizado: " + Driver);
		}

		return conexao;
	}
}
