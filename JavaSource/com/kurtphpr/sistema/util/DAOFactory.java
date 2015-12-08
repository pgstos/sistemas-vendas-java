package com.kurtphpr.sistema.util;

import com.kurtphpr.sistema.cliente.ClienteDAO;
import com.kurtphpr.sistema.cliente.ClienteDAOHibernate;
import com.kurtphpr.sistema.vendas.HibernateUtil;

public class DAOFactory {

	public static ClienteDAO criaClienteDAO() {
		ClienteDAOHibernate clienteDBOHibernate = new ClienteDAOHibernate();
		clienteDBOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());		
		return clienteDBOHibernate;
	}
}
