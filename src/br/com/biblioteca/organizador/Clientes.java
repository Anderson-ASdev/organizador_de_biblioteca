package br.com.biblioteca.organizador;

//import java.util.*;
import java.time.*;
import java.time.temporal.*;
import java.math.BigDecimal;
import java.util.*;

public class Clientes {

	private String nome;
	private LocalDate idade;
	private String cpf;
	private int numeroMatricula;
	private static int MatriculasTotais;
	private BigDecimal multa;
	// histórico e emprestimos simultaneos.
	private List<Emprestimo> emprestimosPassados = new ArrayList<>();
	private Emprestimo emprestimoAtual;

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
		this.idade = idade;
		this.nome = nome;
		this.cpf = cPF;
		MatriculasTotais++;
		this.numeroMatricula = (int) ((int) MatriculasTotais + 1000);
	}

	public void devolucao() {
		this.emprestimoAtual.getLivrosEmprestado().setDisponivel(true);
		emprestimosPassados.add(emprestimoAtual);
		this.emprestimoAtual = null;
	}

	public boolean verificarAtraso() {
		if (LocalDate.now().compareTo(this.emprestimoAtual.getDataDevolucao()) > 0) {
			this.emprestimoAtual.setAtrasado(true);
		}
		return this.emprestimoAtual.isAtrasado();
	}

	public void calcularMulta() {
		int diasAtraso = (int) ChronoUnit.DAYS.between(this.emprestimoAtual.getDataDevolucao(), LocalDate.now());
		if (diasAtraso > 0) {
			BigDecimal multa = new BigDecimal(8.50);
			BigDecimal jurosPorDia = new BigDecimal(1.50).multiply(new BigDecimal(diasAtraso));
			multa = multa.add(jurosPorDia);
			this.multa = multa;
		}
	}

	public void pagarMulta() {
		this.multa = new BigDecimal(0);
		this.emprestimoAtual.setDataDevolucao(LocalDate.now());
	}

	@Override
	public String toString() {
		return "NOME: " + this.nome.toUpperCase() + ", Matricula: " + this.numeroMatricula;
	}

	// getters Padrões
	public String getNome() {
		return nome;
	}

	public LocalDate getIdade() {
		return idade;
	}

	public String getCPF() {
		return cpf;
	}

	public int getNumeroMatricula() {
		return numeroMatricula;
	}

	public static int getMatriculasTotais() {
		return MatriculasTotais;
	}

	public Emprestimo getEmprestimoAtual() {
		return emprestimoAtual;
	}

	public void setEmprestimoAtual(Emprestimo emprestimoAtual) {
		this.emprestimoAtual = emprestimoAtual;
	}

	// setter para teste
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.emprestimoAtual.setDataDevolucao(dataDevolucao);
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(int i) {
		// TODO Stub de método gerado automaticamente

	}
}
