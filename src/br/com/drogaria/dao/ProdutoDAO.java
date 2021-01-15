package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;
import br.com.drogaria.util.JSFUtil;

public class ProdutoDAO {
	
	public void gravarProduto(Produto pro) {
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		try{
			if(conn!=null){
				sql = "INSERT INTO PRODUTO"
						+ " (DESCRICAO, PRECO, QTD, CODFAB)"
						+ " VALUES(?,?,?,?);";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,pro.getDescricao());
				st.setDouble(2, pro.getPreco());
				st.setLong(3, pro.getQuantidade());
				st.setLong(4,  Integer.parseInt(pro.getFabricante().getCodFab()));
				
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
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
	
	public ArrayList<Produto> listar(){
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		conn = cf.getConnection();
		try {
			
			
			if (conn != null) {
				
				/*sql = "SELECT P.CODPRO, P.DESCRICAO, P.PRECO, P.QUANTIDADE,"
						+ " F.CODFAB, F.DESCRICAO"
						+ " FROM PRODUTO P INNER JOIN FABRICANTE F "
						+ "ON F.CODFAB = P.CODFAB;";*/
				sql = "SELECT P.CODPRO, P.DESCRICAO AS DESCRICAOPRO, P.PRECO, P.QTD,"
						+ " F.CODFAB, F.DESCRICAO AS DESCRICAOFAB"
						+ " FROM PRODUTO P INNER JOIN FABRICANTE F "
						+ "ON F.CODFAB = P.CODFAB;";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				ResultSet result = st.executeQuery();
				
				while(result.next()) {
					
					Fabricante f = new Fabricante();
					
					f.setCodFab(result.getString("CODFAB"));
					f.setDescricao(result.getString("DESCRICAOFAB"));
					
					Produto p = new Produto();
					
					p.setCodPro(String.valueOf(result.getInt("CODPRO")));
					p.setDescricao(result.getString("DESCRICAOPRO"));
					p.setPreco(result.getFloat("PRECO"));
					p.setQuantidade(result.getInt("QTD"));
					p.setFabricante(f);
					
					lista.add(p);
				}
				
				st.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
		
		return lista;
	}
	
	public void excluir(Produto pro) {
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		
		try{
			if(conn!=null){
				sql = "DELETE FROM PRODUTO WHERE CODPRO = ?;";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setInt(1,Integer.parseInt(pro.getCodPro()));
				
				st.execute();
				
				st.close();
				conn.close();
				
				JSFUtil.addMensagemSucesso("REMOVIDO!!");
			}
		}catch(SQLException e){
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
	
	public void alterar(Produto pro){
		
		ConexaoFactory cf = new ConexaoFactory();
		Connection conn = null;
		String sql = null;
		conn = cf.getConnection();
		
		try{
			if(conn!=null){
				sql = "UPDATE PRODUTO SET DESCRICAO = ?, PRECO = ?, "
						+ "QTD = ?, CODFAB = ?"
						+ " WHERE CODPRO = ?;";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,pro.getDescricao());
				st.setFloat(2,pro.getPreco());
				st.setInt(3,pro.getQuantidade());
				st.setInt(4, Integer.parseInt(pro.getFabricante().getCodFab()));
				st.setInt(5,Integer.parseInt(pro.getCodPro()));
			
				st.execute();
				
				st.close();
				conn.close();
				
				JSFUtil.addMensagemSucesso("EDITADO COM SUCESSO!!");
			}
		}catch(SQLException e){
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
}