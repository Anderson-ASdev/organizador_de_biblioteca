package br.com.biblioteca.organizador;

//import java.util.*;
import java.time.*;
import java.time.temporal.*;
import java.math.BigDecimal;

public class Clientes {

	private String Nome;
	private LocalDate Idade;
	private String CPF;
	//transformar CPF em objeto.
	private int NumeroMatricula;
	private static int MatriculasTotais;
	private double multa;
	//transformar emprestimo em um objeto e transferir metodos. E assim desenvolver histórico e emprestimos simultaneos.
	private Livros LivrosEmprestado;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;

	public Clientes(String nome, String cPF, int ano, int mes, int dia) {
		if (nome == null || cPF == null || ano == 0 || mes == 0 || dia == 0) {
			throw new NullPointerException("Preencher dados do Cliente");
		}
		LocalDate idade = LocalDate.of(ano, mes, dia);
		LocalDate idadeMinima = LocalDate.now().minusYears(16);
		if (idadeMinima.compareTo(idade) < 0) {
			throw new DateTimeException("O cliente é menor que 16 anos.");
		}
		if (LocalDate.now().compareTo(idade) < 0) {
			throw new DateTimeException("A data é invalida");
		}
		this.Idade = idade;
		this.Nome = nome;
		this.CPF = cPF;
		MatriculasTotais++;
		this.NumeroMatricula = (int) ((int) MatriculasTotais * 1000 * Math.random());
	}
	public void devolverLivro() {
		if (emprestimoAtrasado()) {
			int diasAtraso = (int) ChronoUnit.DAYS.between(dataDevolucao, LocalDate.now());
			calcularMulta(diasAtraso);
			throw new RuntimeException("Seu imprestimo esta " + diasAtraso 
					+ " dias Atrasado. Para devolver o Livro pague a multa de R$" + this.multa);
		}
			this.LivrosEmprestado.setDisponivel(true);
			this.LivrosEmprestado = null;
			this.dataEmprestimo = null;
			this.dataDevolucao = null;
		}

	private BigDecimal calcularMulta(int diasAtraso) {
		BigDecimal multa = new BigDecimal(8.50);
		BigDecimal jurosPorDia = new BigDecimal(2.50).multiply(new BigDecimal(diasAtraso));
		multa = multa.add(jurosPorDia);
		return multa;
	}
	
	
	public void pagarMulta() {
		this.multa = 0;
		this.dataDevolucao = LocalDate.now();
	}
	public boolean emprestimoAtrasado() {
		return LocalDate.now().compareTo(dataDevolucao) > 0;

	}
	public boolean isLivroEmprestado() {
		if (this.LivrosEmprestado != null) {
			return true;
		}
			return false;
		}
	// metodo unico EmprestimoDeLivros
	public void EmprestaLivros(Livros livro) {

		if (!livro.isDisponivel()) {
			throw new RuntimeException("O livro" + livro.toString() + "Não está disponivel");
		}
		if (isLivroEmprestado()) {
			throw new RuntimeException("Já possui um emprestimo em andamento");
		}
		
			this.dataEmprestimo = LocalDate.now();
			this.dataDevolucao = dataEmprestimo.plusDays(20);

			if (dataDevolucao.getDayOfWeek().getValue() == 7) { // Caso seja Domingo
				this.dataDevolucao = dataDevolucao.plusDays(1);
			}
			this.LivrosEmprestado = livro;
			this.LivrosEmprestado.setDisponivel(false);
		}

	@Override
	public String toString() {
		return "NOME: " + this.Nome.toUpperCase() + ", CPF: " + this.CPF;
	}

	// getters Padrões
	public String getNome() {
		return Nome;
	}

	public LocalDate getIdade() {
		return Idade;
	}

	public String getCPF() {
		return CPF;
	}

	public int getNumeroMatricula() {
		return NumeroMatricula;
	}

	public static int getMatriculasTotais() {
		return MatriculasTotais;
	}

	public Livros getLivrosEmprestado() {
		return LivrosEmprestado;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolução() {
		return dataDevolucao;
	}

	// setter para teste
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public double getMulta() {
		return multa;
	}
}
