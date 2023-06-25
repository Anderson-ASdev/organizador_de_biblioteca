package br.com.biblioteca.organizador;


//import java.util.*;
//import java.time.*;

public class Testes {
public static void main(String[] args) {
	Biblioteca bb = biblioteca();
	for (Livros livro : bb.consultarLivrosDisponiveis()) {
		System.out.println(livro.toString());
	}
	for (Clientes cliente : bb.getClientesRegistrados()) {
		System.out.println(cliente.toString());
	}
	bb.EmprestaLivros(1898616064, 1001);
	bb.EmprestaLivros(1305467652, 1002);
	System.out.println(bb.verificarClienteComEmprestimo());
}
private static Biblioteca biblioteca() {
	Biblioteca bb = new Biblioteca();
	bb.adicionarLivro(2020, "Autor 1", "Titulo 1", "arte");
	bb.adicionarLivro(2015, "Autor 2", "Titulo 2", "musica");
	bb.adicionarLivro(2008, "Autor 3", "Titulo 3", "cinema");
	bb.adicionarLivro(2012, "Autor 4", "Titulo 4", "fotografia");
	bb.adicionarLivro(2019, "Autor 5", "Titulo 5", "esportes");
	bb.adicionarLivro(2005, "Autor 6", "Titulo 6", "romance");
	bb.adicionarLivro(2018, "Autor 7", "Titulo 7", "ficcaocientifica");
	bb.adicionarLivro(2010, "Autor 8", "Titulo 8", "fantasia");
	bb.adicionarLivro(2003, "Autor 9", "Titulo 9", "misterio");
	bb.adicionarLivro(2017, "Autor 10", "Titulo 10", "aventura");
	bb.adicionarLivro(2014, "Autor 11", "Titulo 11", "suspense");
	bb.adicionarLivro(2006, "Autor 12", "Titulo 12", "terror");
	//clientes
	bb.adicionarCliente("Cliente 1", "CPF1", 1990, 1, 1);
	bb.adicionarCliente("Cliente 2", "CPF2", 1985, 2, 15);
	bb.adicionarCliente("Cliente 3", "CPF3", 1992, 6, 10);
	bb.adicionarCliente("Cliente 4", "CPF4", 1988, 11, 25);
	bb.adicionarCliente("Cliente 5", "CPF5", 2007, 4, 3);
	return bb;
}
}
