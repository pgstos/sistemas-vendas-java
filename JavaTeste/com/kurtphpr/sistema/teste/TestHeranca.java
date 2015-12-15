package com.kurtphpr.sistema.teste;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.kurtphpr.sistema.vendas.HibernateUtil;

public abstract class TestHeranca {
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
	
}
