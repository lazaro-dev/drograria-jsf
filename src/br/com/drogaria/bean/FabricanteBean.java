package br.com.drogaria.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public ArrayList<Fabricante> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct  /*roda antes de exibi (carrega dados quando a tela é carregada)*/
	public void prepararPesquisa(){
		try{
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.getListarFabricante();
			
		}catch(Exception e) {
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
	public void prepararNovo(){
		fabricante = new Fabricante(); 
	}
	public void novoFabricante(){
		//try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.gravarFabricante(fabricante);
			
			itens = dao.getListarFabricante(); 
			
			JSFUtil.addMensagemSucesso("Operação relizada com sucesso");
			
		//} catch (Exception e) {
			//e.printStackTrace();
			//JSFUtil.addMensagemErro(e.getMessage());
		//}
	}
	

	
	public void excluir() {
		try {
		FabricanteDAO dao = new FabricanteDAO();
		dao.excluirFabricante(fabricante);
		
		itens = dao.getListarFabricante();
		
		JSFUtil.addMensagemSucesso("Fabricante Removido!!!");
		}catch(Exception e){
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
	
	
	public void editar() {
		try {
		FabricanteDAO dao = new FabricanteDAO();
		dao.alterarFabricante(fabricante);
		
		itens = dao.getListarFabricante();
		
		JSFUtil.addMensagemSucesso("Fabricante Editado!!!");
		}catch(Exception e){
			e.printStackTrace();
			JSFUtil.addMensagemErro(e.getMessage());
		}
	}
	
}
