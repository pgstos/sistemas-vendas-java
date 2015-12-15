package com.kurtphpr.sistema.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.kurtphpr.sistema.Produto.Produto;
import com.kurtphpr.sistema.cliente.Cliente;
import com.kurtphpr.sistema.cliente.ClienteRN;

//import org.junit.Test;

public class VendaTest extends TestHeranca {
    
	Cliente c1,c2,c3;
	Produto p1,p2,p3;
	
	@Before
	public void setup(){
		c1 = new Cliente("71246856131","testeum@teste.com.br", "Rua: um", "Cliente um", new Date(), 1000);
		c2 = new Cliente("71246856131","testedois@teste.com.br", "Rua: dois", "Cliente dois", new Date(), 2000);
		c3 = new Cliente("71246856131","testetres@teste.com.br", "Rua: tres", "Cliente tres", new Date(), 3000);
		
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);
		
		p1 = new Produto("lote","Caderno",new Date(),58,9.45f);	
		p2 = new Produto("unidade","Régua",new Date(),30,2.5f);
		p3 = new Produto("fardo","Arroz",new Date(),353,10.5f);
		
		ProdutoRN produtoRN = new ProdutoRN();
		
		produtoRN.salvar(p1);
		produtoRN.salvar(p2);
		produtoRN.salvar(p3);
				
	}  
	
	@Test
	public void registraTest(){
		
        VendaRN vendaRN = new VendaRN();
		
		vendaRN.registraVenda(c1,p1);
		vendaRN.registraVenda(c2,p2);
		vendaRN.registraVenda(c3,p3);
		
		List<Venda> vendas = vendaRN.vendas();
		
		assertEquals(3, vendas.size());
		
	}
	
	
	
}
