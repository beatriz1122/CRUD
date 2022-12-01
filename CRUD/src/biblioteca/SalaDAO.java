package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO implements DaoSala {
	
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	private Connection con;

	public SalaDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL,user,pass);
			
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		 	
}
	
	@Override
	public void create (Sala s) {
		String sql = "INSERT INTO sala (sala,tipo)"
		+ "VALUES ('"+ s.getNome()+"', '"+s.getTipo()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Sala> pesquisarPorNome(String nome) {
		List <Sala> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM sala WHERE sala LIKE '%"+ nome +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Sala s = new Sala();
			s.setNome(rs.getString("nome"));
			s.setTipo(rs.getString("autor"));
			
			
			lista.add(s);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void delete(Sala s) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM sala "
				+ "WHERE sala = '"+ s.getNome()+"'";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Sala s) {
		String sql = "UPDATE sala SET sala = '"+ s.getNome()+"', tipo = '"+s.getTipo()+"' WHERE sala = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
		
	}



}
