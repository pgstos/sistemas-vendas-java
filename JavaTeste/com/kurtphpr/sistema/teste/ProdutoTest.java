package com.kurtphpr.sistema.teste;

import static org.junit.Assert.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;


//import javax.persistence.Query;
import org.hibernate.Query;

import com.kurtphpr.sistema.Produto.Produto;
import com.kurtphpr.sistema.Produto.ProdutoRN;
import com.kurtphpr.sistema.vendas.HibernateUtil;
import com.sun.org.apache.xpath.internal.operations.Equals;

public class ProdutoTest extends TestHeranca {
	
	@Before
	public void setup(){
	   Produto p1 =	new Produto("lote","Caderno",new Date(),58,9.45f);	
	   Produto p2 =	new Produto("unidade","Régua",new Date(),30,2.5f);
	   Produto p3 =	new Produto("fardo","Arroz",new Date(),353,10.5f);
	   Produto p4 =	new Produto("caixa","Copos",new Date(),530,9.15f);
	   Produto p5 =	new Produto("peça","Tapete",new Date(),345,14.5f);
	   Produto p6 =	new Produto("litro","Leite",new Date(),56,5.75f);
	   
	   ProdutoRN produtoRN = new ProdutoRN();
	   produtoRN.salvar(p1);
	   produtoRN.salvar(p2);
	   produtoRN.salvar(p3);
	   produtoRN.salvar(p4);
	   produtoRN.salvar(p5);
	}
	
	@After
	public void limpaBanco(){
		//Criteria lista = sessao.createCriteria(Produto.class);
		//@SuppressWarnings("unchecked")
				
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> produtos = produtoRN.listar();
		
		for (Produto produto : produtos){
			produtoRN.excluir(produto);
		}
		
	}
	
	@Test
	public void salvarProdutoTeste(){
		
	  // Query consulta = pesquisar("Caderno");
	  // Produto produtoPesquisado = (Produto) consulta.uniqueResult();		
		ProdutoRN produtoRN = new ProdutoRN();
		
		Produto produtoSalvo =	new Produto("lote33","Apontador",new Date(),58,9.45f);	
		
		//Produto produtoSalvo = new Produto();
		
		produtoRN.salvar(produtoSalvo);
		
		Produto produtoPesquisado = produtoRN.pesquisarPorDescricao("Apo");
		
	   assertEquals("lote33",produtoPesquisado.getUnidade());
		
	}
	
	@Test
	public void listaProdutosTest(){		
	   //Criteria lista = sessao.createCriteria(Produto.class);
	   //@SuppressWarnings("unchecked")
	  // List<Produto> produtos = lista.list();
		
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> produtos = produtoRN.listar();
	   
	   
	   assertEquals(6, produtos.size());		
	}
	
	/*@Test
	   public void alteracaoProdutoTest(){	   
		   Query consulta = pesquisar("Caderno");
		   Produto produtoAlterado = (Produto) consulta.uniqueResult();
		  produtoAlterado.setEstoque(100);
		  sessao.update(produtoAlterado);
		  assertEquals(100, produtoAlterado.getEstoque().intValue());   
	   }*/
	
//   @Test
//   public void excluirProdutoTest(){	   
//	  Query consulta = pesquisar("Tapete");
//	  Produto produtoDeletado = (Produto) consulta.uniqueResult();
//	  sessao.delete(produtoDeletado);	    
//	  produtoDeletado = (Produto) ((org.hibernate.Query) consulta).uniqueResult();
//	  assertNull(produtoDeletado);   
//   }
   
//   private Query pesquisar(String parametro) {
//	  String sql = "from Produto p where p.descricao like :descricao";
//	  Query consulta = (Query) sessao.createQuery(sql);
//	  consulta.setString("descricao","%"+parametro+"%");
//	  return consulta;
//}
}
