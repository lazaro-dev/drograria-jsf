package br.com.drogaria.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Fabricante> comboFabricantes;
	
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public void carregarListagem(){
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();			
	}
	
	public void prepararNovo() {
		produto = new Produto();
		FabricanteDAO dao = new FabricanteDAO();
		comboFabricantes = dao.getListarFabricante();
	}
	
	public void novoProduto(){
		ProdutoDAO dao = new ProdutoDAO();
		dao.gravarProduto(produto); 
		itens = dao.listar();
	}
	
	public void excluirProduto(){
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(produto);
		itens = dao.listar();
	}
	
	public void prepararEditar() {
		FabricanteDAO dao = new FabricanteDAO();
		comboFabricantes = dao.getListarFabricante();
	}
	
	public void editarProduto(){
		ProdutoDAO dao = new ProdutoDAO();
		dao.alterar(produto);
		itens = dao.listar();
	}
}
