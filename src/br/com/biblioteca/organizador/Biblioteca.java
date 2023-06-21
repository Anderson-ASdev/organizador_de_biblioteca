package br.com.biblioteca.organizador;

import java.util.*;

public class Biblioteca {
	
	//adicionar função para procurar clientes (nome, CPF, Codigo)
	//pesquisar livros por codigo.
	//emprestimo de livro para clientes
	
	
	private Set<Clientes> ClientesRegistrados = new HashSet<Clientes>();
	private Map<Integer, Livros> ColecaoLivros = new HashMap<>();

	public Set<Livros> consultarLivrosDisponiveis() {
		Set<Livros> livrosDisponiveis = new HashSet<>();
		for (Map.Entry<Integer, Livros> livros : ColecaoLivros.entrySet()) {
			Livros livro = livros.getValue();
			if (livro.isDisponivel()) {
				livrosDisponiveis.add(livro);
			}
		}
		return livrosDisponiveis;
	}

	public Set<Clientes> consultarClientes() {
		return Collections.unmodifiableSet(ClientesRegistrados);
	}

	public Set<Clientes> verificarClienteComEmprestimo() {
		Set<Clientes> ClientesComEmprestimo = new HashSet<>();
		for (Clientes clientes : ClientesRegistrados) {
			if(clientes.isLivroEmprestado()) {
				ClientesComEmprestimo.add(clientes);
			}	
		}
		return ClientesComEmprestimo;
	}

	public Set<Clientes> verificarAtrasos() {
		Set<Clientes> ClientesComAtraso = new HashSet<>();
		for (Clientes clientes : verificarClienteComEmprestimo()) {
			if(clientes.isLivroEmprestado()) {
				ClientesComAtraso.add(clientes);
			}
		}
		return ClientesComAtraso;
	}
		public void adicionarLivro(int ano, String autor, String titulo, String genero) {
		
		Livros livro = new Livros(ano, autor, titulo, genero);
		Integer codigo = (int) livro.getID();
		ColecaoLivros.put(codigo, livro);
		
		
	}

	public void adicionarCliente(String nome, String cPF, int ano, int mes, int dia) {
		ClientesRegistrados.add(new Clientes(nome, cPF, ano, mes, dia));
	}

}
