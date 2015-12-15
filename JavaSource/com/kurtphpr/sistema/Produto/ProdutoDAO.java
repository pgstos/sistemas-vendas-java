package com.kurtphpr.sistema.Produto;

import java.util.List;

public interface ProdutoDAO {

	public void salvar(Produto produto);

	public List<Produto> listar();

	public Produto pesquisarPorDescricao(String descricao);

	public void exluir(Produto produto);

}
