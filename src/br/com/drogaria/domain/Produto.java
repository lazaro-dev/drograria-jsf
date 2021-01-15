package br.com.drogaria.domain;

public class Produto {
	private String codPro;
	private String descricao;
	private int quantidade;
	private float preco;
	private Fabricante fabricante;
	
	public Produto(){//funciona sem instanciar
		this.fabricante = new Fabricante();
	}
	
	public String getCodPro() {
		return this.codPro;}
	public void setCodPro(String codPro) {
		this.codPro = codPro;}
	public String getDescricao() {
		return this.descricao;}
	public void setDescricao(String descricao) {
		this.descricao = descricao;}
	public int getQuantidade() {
		return this.quantidade;}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;}
	public float getPreco() {
		return this.preco;}
	public void setPreco(float preco) {
		this.preco = preco;}
	
	public Fabricante getFabricante() {
		return this.fabricante;}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;}
	
}
