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
import com.kurtphpr.sistema.vendas.HibernateUtil;
import com.sun.org.apache.xpath.internal.operations.Equals;

public class ProdutoTest {
      
	private static Session sessao;
	private static Transaction transacao;
	
	@BeforeClass
	public static void abreConexao(){
		sessao = HibernateUtil.getSession().getCurrentSession();
		transacao = sessao.beginTransaction();
	}
	
	@AfterClass
	public static void fechaConexao(){
		
		try {
			transacao.commit();
			
		} catch (Throwable e) {
		System.out.println("Deu problema no commit: "+e.getMessage());
		}finally{
			try {
				if(sessao.isOpen()){
					sessao.close();
				}
				
			} catch (Exception e) {
				System.out.println("deu erro no fechamento da sessão "+e.getMessage());
			}
			
		}
	}
	
	@Before
	public void setup(){
	   Produto p1 =	new Produto("lote","Caderno",new Date(),58,9.45f);	
	   Produto p2 =	new Produto("unidade","Régua",new Date(),30,2.5f);
	   Produto p3 =	new Produto("fardo","Arroz",new Date(),353,10.5f);
	   Produto p4 =	new Produto("caixa","Copos",new Date(),530,9.15f);
	   Produto p5 =	new Produto("peça","Tapete",new Date(),345,14.5f);
	   Produto p6 =	new Produto("litro","Leite",new Date(),56,5.75f);
	   
	   sessao.save(p1);
	   sessao.save(p2);
	   sessao.save(p3);
	   sessao.save(p4);
	   sessao.save(p5);
	   sessao.save(p6);		
	}
	
	@After
	public void limpaBanco(){
		Criteria lista = sessao.createCriteria(Produto.class);
		@SuppressWarnings("unchecked")
		List<Produto> produtos = lista.list();
		for (Produto produto : produtos){
			sessao.delete(produto);
		}		
	}
	
	@Test
	public void salvarProdutoTeste(){
	   Query consulta = pesquisar("Caderno");
	   Produto produtoPesquisado = (Produto) consulta.uniqueResult();		
	   assertEquals("lote",produtoPesquisado.getUnidade());
		
	}
	
	@Test
	public void listaProdutosTest(){		
	   Criteria lista = sessao.createCriteria(Produto.class);
	   @SuppressWarnings("unchecked")
	   List<Produto> produtos = lista.list();		
	   assertEquals(6, produtos.size());		
	}
	
	@Test
	   public void alteracaoProdutoTest(){	   
		   Query consulta = pesquisar("Caderno");
		   Produto produtoAlterado = (Produto) consulta.uniqueResult();
		  produtoAlterado.setEstoque(100);
		  sessao.update(produtoAlterado);
		  assertEquals(100, produtoAlterado.getEstoque().intValue());   
	   }
	
   @Test
   public void excluirProdutoTest(){	   
	  Query consulta = pesquisar("Tapete");
	  Produto produtoDeletado = (Produto) consulta.uniqueResult();
	  sessao.delete(produtoDeletado);	    
	  produtoDeletado = (Produto) ((org.hibernate.Query) consulta).uniqueResult();
	  assertNull(produtoDeletado);   
   }
   
   private Query pesquisar(String parametro) {
	  String sql = "from Produto p where p.descricao like :descricao";
	  Query consulta = (Query) sessao.createQuery(sql);
	  consulta.setString("descricao","%"+parametro+"%");
	  return consulta;
}
}
