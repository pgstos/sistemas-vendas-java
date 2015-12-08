package com.kurtphpr.sistema.teste;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kurtphpr.sistema.cliente.Cliente;
import com.kurtphpr.sistema.cliente.ClienteRN;
import com.kurtphpr.sistema.vendas.HibernateUtil;

public class ClienteTest {
	
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
		Cliente c1 = new Cliente("71246856131","testeum@teste.com.br", "Rua: um", "Cliente um", new Date(), 1000);
		Cliente c2 = new Cliente("71246856131","testedois@teste.com.br", "Rua: dois", "Cliente dois", new Date(), 2000);
		Cliente c3 = new Cliente("71246856131","testetres@teste.com.br", "Rua: tres", "Cliente tres", new Date(), 3000);
		
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);
		
	}
	
	@After
	public void limpaBanco(){
		
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista = clienteRN.listar();
		for(Cliente cliente : lista){
			clienteRN.excluir(cliente);
		}
		
	}
	
	
	@Test
	public void salvarTest(){
		Cliente c1 = new Cliente();
		
		//c1.setNome("Ronaldo");
		//c1.setEndereco("Rua Teste");
		//c1.setRenda(5000f);
		//c1.setCpf("1234567891");
		
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(c1);
		
		assertEquals(true,true);
		
	}
	
	@Test
	public void listarTest(){
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista = clienteRN.listar();
		assertEquals(3,lista.size());
	}
	
	@Test
	public void excluirTest(){
		ClienteRN clienteRN = new ClienteRN();
		
		List<Cliente> lista = clienteRN.listar();
		
		Cliente clienteExcluido = lista.get(0);
		
		clienteRN.excluir(clienteExcluido);
		
		lista = clienteRN.listar();
		
		assertEquals(2,lista.size());
	}
	
	@Test
	public void pesquisarTest(){
		
		ClienteRN clienteRn = new ClienteRN();
		Cliente clientePesquisado = clienteRn.pesquisar("te u");
		
		assertEquals("testeum@teste.com.br", clientePesquisado.getEmail());
		
	}
	
	@Test
	public void alterarTest(){
		ClienteRN clienteRN = new ClienteRN();
		Cliente clientePesquisado = clienteRN.pesquisar("te u");
		
		assertEquals("testeum@teste.com.br", clientePesquisado.getEmail());
		
		clientePesquisado.setEndereco("Novo Endereço");
		
		clienteRN.alterar(clientePesquisado);
		
		Cliente clienteAlterado = clienteRN.pesquisar("te u");
		
		assertEquals("Novo Endereço", clienteAlterado.getEndereco());
		
		
	}
	

}
