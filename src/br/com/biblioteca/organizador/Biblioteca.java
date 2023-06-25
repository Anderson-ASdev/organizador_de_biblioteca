package br.com.biblioteca.organizador;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
	private Map<Integer, Clientes> ClientesRegistrados = new HashMap<>();
	private Map<Integer, Livros> ColecaoLivros = new HashMap<>();

	// livros
	public void adicionarLivro(int ano, String autor, String titulo, String genero) {

		Livros livro = new Livros(ano, autor, titulo, genero);
		Integer codigo = (int) livro.getID();
		ColecaoLivros.put(codigo, livro);

	}

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

	public Livros consultarLivro(Integer idLivro) {
		if (!ColecaoLivros.containsKey(idLivro)) {
			throw new NoSuchElementException("O ID: " + idLivro + ", não está registrado no sistema");
		}
		return ColecaoLivros.get(idLivro);

	}

	// clientes
	public void adicionarCliente(String nome, String cPF, int ano, int mes, int dia) {
		Clientes cliente = new Clientes(nome, cPF, ano, mes, dia);
		Integer codigo = cliente.getNumeroMatricula();
		ClientesRegistrados.put(codigo, cliente);
	}

	public Set<Clientes> verificarClienteComEmprestimo() {
		Set<Clientes> ClientesComEmprestimo = new HashSet<>();
		for (Map.Entry<Integer, Clientes> clienteRegistrado : ClientesRegistrados.entrySet()) {
			Clientes cliente = clienteRegistrado.getValue();
			if (cliente.getEmprestimoAtual() != null) {
				ClientesComEmprestimo.add(cliente);
			}
		}
		return ClientesComEmprestimo;
	}

	public Set<Clientes> verificarAtrasos() {
		Set<Clientes> ClientesComAtraso = new HashSet<>();
		for (Clientes cliente : verificarClienteComEmprestimo()) {
			if (cliente.getEmprestimoAtual().isAtrasado()) {
				ClientesComAtraso.add(cliente);
			}
		}
		return ClientesComAtraso;
	}

	public Clientes verificarCliente(int matricula) {
		if (!ClientesRegistrados.containsKey(matricula)) {
			throw new NoSuchElementException("A Matricula: " + matricula + ", não está registrado no sistema");
		}
		return ClientesRegistrados.get(matricula);
	}

	public int verificarMatricula(String cpf) {
		for (Map.Entry<Integer, Clientes> clienteRegistrado : ClientesRegistrados.entrySet()) {
			Clientes cliente = clienteRegistrado.getValue();
			if (cliente.getCPF() == cpf) {
				return cliente.getNumeroMatricula();
			}
		}
		throw new NoSuchElementException("O CPF: " + cpf + ", não está registrado no sistema");
	}

	// emprestimo
	public void EmprestaLivros(Integer idLivro, int matricula) {
		Livros livro = consultarLivro(idLivro);
		Clientes cliente = verificarCliente(matricula);
		Emprestimo emprestimoAtual = new Emprestimo();
		if (!livro.isDisponivel()) {
			throw new RuntimeException("O livro" + livro.toString() + "Não está disponivel");
		}
		if (cliente.getEmprestimoAtual() != null) {
			throw new RuntimeException("Já possui um emprestimo em andamento");
		}
		LocalDate dataDevolucao = LocalDate.now().plusDays(20);
		if (dataDevolucao.getDayOfWeek().getValue() == 7) { // Caso seja Domingo
			dataDevolucao.plusDays(1);
		}
		emprestimoAtual.setDataEmprestimo(LocalDate.now());
		emprestimoAtual.setDataDevolucao(dataDevolucao);
		emprestimoAtual.setLivrosEmprestado(livro);
		cliente.setEmprestimoAtual(emprestimoAtual);
		livro.setDisponivel(false);
	}

	public void devolverLivro(int matricula) {
		Clientes cliente = verificarCliente(matricula);

		if (cliente.verificarAtraso()) {
			cliente.calcularMulta();
			throw new RuntimeException(
					"Seu imprestimo esta atrasado. Para devolver o Livro pague a multa de R$" + cliente.getMulta());
		}
		cliente.devolucao();
	}

	public void devolverAtrasado(int matricula) {
		Clientes cliente = verificarCliente(matricula);
		cliente.pagarMulta();
		devolverLivro(matricula);
	}

	//teste clientes
	public Set<Clientes> getClientesRegistrados() {
		Set<Clientes> ClientesComEmprestimo = new HashSet<>();
		for (Map.Entry<Integer, Clientes> clienteRegistrado : ClientesRegistrados.entrySet()) {
			Clientes cliente = clienteRegistrado.getValue();
				ClientesComEmprestimo.add(cliente);
		}
		return ClientesComEmprestimo;
	}
	}
	
