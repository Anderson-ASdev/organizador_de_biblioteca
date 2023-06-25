package br.com.biblioteca.organizador;

import java.util.*;
import java.util.Map.Entry;
import java.time.*;

public class Livros {
	private String titulo;
	private String autor;
	private int ano;
	private boolean disponivel = true;

	private int ID;
	private String genero;
	private String tipo;

	private static Map<String, Map<String, String>> tipos = new HashMap<>();
	private static int total;
	static {
		Map<String, String> ArtesLazer = new HashMap<>();
		Map<String, String> Ficcao = new HashMap<>();
		Map<String, String> NaoFiccao = new HashMap<>();
		Map<String, String> Educacao = new HashMap<>();
		Map<String, String> Espiritual = new HashMap<>();
		Map<String, String> InfantoJuvenil = new HashMap<>();

		// adiciona os HashMap a Lista primaria
		tipos.put("Ficção",Ficcao);
		tipos.put("Não Ficção",NaoFiccao);
		tipos.put("Artes e Lazer",ArtesLazer);
		tipos.put("Educação",Educacao);
		tipos.put("Espiritualidade",Espiritual);
		tipos.put("Infantojuvenil",InfantoJuvenil);

		// Adiciona os generos ao HashMap onde, Key é a palavra sem formatação, e o
		// valor é o adicionado ou impresso quando pedido.
		ArtesLazer.put("arte", "Arte");
		ArtesLazer.put("musica", "Música");
		ArtesLazer.put("cinema", "Cinema");
		ArtesLazer.put("fotografia", "Fotografia");
		ArtesLazer.put("esportes", "Esportes");

		Ficcao.put("romance", "Romance");
		Ficcao.put("ficcaocientifica", "Ficção Científica");
		Ficcao.put("fantasia", "Fantasia");
		Ficcao.put("misterio", "Mistério");
		Ficcao.put("aventura", "Aventura");
		Ficcao.put("suspense", "Suspense");
		Ficcao.put("terror", "Terror");
		Ficcao.put("classicos", "Clássicos");

		NaoFiccao.put("biografia", "Biografia");
		NaoFiccao.put("historia", "História");
		NaoFiccao.put("ciencias", "Ciências");
		NaoFiccao.put("filosofia", "Filosofia");
		NaoFiccao.put("psicologia", "Psicologia");
		NaoFiccao.put("autoajuda", "Autoajuda");
		NaoFiccao.put("economia", "Economia");

		Educacao.put("didaticos", "Didáticos");
		Educacao.put("dicionarios", "Dicionários");
		Educacao.put("guias", "Guias");

		Espiritual.put("religiosos", "Religiosos");
		Espiritual.put("espiritualidade", "Espiritualidade");

		InfantoJuvenil.put("contos", "Contos");
		InfantoJuvenil.put("juvenil", "Juvenil");
	}
	
	public Livros(int ano, String autor, String titulo, String genero) {
		if(autor == null || titulo == null || genero == null) {
			throw new NullPointerException("Nenhuma das inserções podem ser nulas");
		}
		if(ano > LocalDate.now().getYear()) {
			throw new DateTimeException("O ano do livro é maior que a data atual");
		}
		this.ano = ano;
		this.autor = autor;
		this.titulo = titulo;
		setGenero(genero);
		total++;
	}

	public void setGenero(String genero) {
		
		for (Entry<String, Map<String, String>> generos : tipos.entrySet()) {
			if (generos.getValue().containsKey(genero)) {
				this.genero = generos.getValue().get(genero);
				this.tipo = generos.getKey().toString();
				setID();
				return;
			}
		}
		throw new NoSuchElementException("Opção " + genero + "não registrada no sistema");
	}

	protected void setID() {
		int autor = somaString(this.autor);
		int titulo = somaString(this.titulo);
		int genero = somaString(this.genero);
		int tipo = somaString(this.tipo);

		int ID = Math.abs(tipo * genero * this.ano * autor * titulo);
		while(ID < 999999999) {
			ID += ID*0.69;
		}
		this.ID = ID;
	}
	private int somaString(String string) {
		int somaString = 0;
		for (int i = 0; i < string.length(); i++) {
			somaString += (int) string.charAt(i);
		}
		return somaString;
	}

	// setter

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	// getter

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getGenero() {
		return genero;
	}

	public int getAno() {
		return ano;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public long getID() {
		return ID;
	}

	public int getTotal() {
		return total;
	}
	public String getTipo() {
		return tipo;
	} 
@Override
public String toString() {
	
	return this.ID +": " + this.titulo + ". " + this.ano;
}

}
