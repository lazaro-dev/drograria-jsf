package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test
	@Ignore 
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("ANADOR 45 COMPRIMIDOS");
		p.setPreco(2.45F);
		p.setQuantidade(15);
		
		Fabricante f = new Fabricante();
		f.setCodFab("1");
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.gravarProduto(p);
	}
	@Test
	@Ignore
	public void listar() throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p: lista) {
			System.out.println("Codigo Produto: "+p.getCodPro());
			System.out.println("Descricao: "+p.getDescricao());
			System.out.println("Preço: "+p.getPreco());
			System.out.println("Quantidade: "+p.getQuantidade());
			System.out.println("Codigo Fabricante: "+p.getFabricante().getCodFab());
			System.out.println("Descricao: "+p.getFabricante().getDescricao());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void excluir()throws SQLException{
		Produto pro = new Produto();
		
		pro.setCodPro("4");
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.excluir(pro);
	}
	
	@Test
	public void alterar() throws SQLException{
		
		Produto pro = new Produto();
		
		pro.setCodPro("5");
		pro.setDescricao("Cataflan 60 gramas");
		pro.setPreco(15.99f);
		pro.setQuantidade(50);
		
		Fabricante fab = new Fabricante();
		
		fab.setCodFab("4");
		
		pro.setFabricante(fab);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.alterar(pro);
	}
}
