package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;


public class FabricanteDAO {
	
	public void gravarFabricante(Fabricante fab){
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		try{
			if(conn!=null){
				sql = "INSERT INTO FABRICANTE (DESCRICAO) VALUES(?)";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,fab.getDescricao());
				
				st.execute();
				
				st.close();
				conn.close();
				/*
				 
				StringBuild sql = new StringBuild();
				sql.append("INSERT INTO FABRICANTE ");
				sql.append("(DESCRICAO) ");
				sql.append("VALUES (?)");  
				  
			 	Connection conexao = ConexaoFactory.getConennction();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setSring(1,f.getDescricao());
				 
				comando.executeUpdate();
				
				*/
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void excluirFabricante(Fabricante fab){
			
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		
		try{
			if(conn!=null){
				sql = "DELETE FROM FABRICANTE WHERE CODFAB = ? ";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setInt(1,Integer.parseInt(fab.getCodFab()));
				
				st.execute();
				
				st.close();
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void alterarFabricante(Fabricante fab){
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		
		try{
			if(conn!=null){
				sql = "UPDATE FABRICANTE SET DESCRICAO = ?  WHERE CODFAB = ? ";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,fab.getDescricao());
				st.setInt(2,Integer.parseInt(fab.getCodFab()));
			
				st.execute();
				
				st.close();
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Fabricante buscarFabricante(Fabricante fab){
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		Fabricante retorno = null;
		conn = cf.getConnection();
		
		try{
			if(conn!=null){
				
				sql = "SELECT CODFAB, DESCRICAO, FROM FABRICANTE WHERE CODFAB = ? ";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				ResultSet result = st.executeQuery();
				
				if(result.next()){
					retorno = new Fabricante();
					retorno.setCodFab(result.getString("CODFAB"));
					retorno.setDescricao(result.getString("DESCRICAO"));
				}
				
				st.setInt(1,Integer.parseInt(fab.getCodFab()));
				
				st.close();
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return retorno;
	}
	public ArrayList<Fabricante> getListarFabricante() {
		
		Fabricante fabricante = new Fabricante();
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		try {
			
			conn = cf.getConnection();
			if (conn != null) {
				
				sql = "SELECT * FROM FABRICANTE DESC ";
				PreparedStatement st = conn.prepareStatement(sql);
				
				ResultSet result = st.executeQuery();
				
				while(result.next()) {
					
					fabricante = new Fabricante();
					
					fabricante.setCodFab(result.getString("CODFAB"));
					fabricante.setDescricao(result.getString("DESCRICAO"));
					
					lista.add(fabricante);
				}
				
				st.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}
public ArrayList<Fabricante> buscarPorDescricao(Fabricante fabricante) {
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		try {
			
			conn = cf.getConnection();
			if (conn != null) {
				
				sql = "SELECT * FROM FABRICANTE WHERE DESCRICAO LIKE ? ORDER BY DESC ";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,"%"+fabricante.getDescricao()+"%");
				ResultSet result = st.executeQuery();
				
				while(result.next()) {
					
					Fabricante item = new Fabricante();
					
					item.setCodFab(result.getString("CODFAB"));
					item.setDescricao(result.getString("DESCRICAO"));
					
					lista.add(item);
					
				}
				
				st.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}
}
