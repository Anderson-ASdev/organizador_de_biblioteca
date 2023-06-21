package br.com.biblioteca.organizador;


//import java.util.*;
//import java.time.*;

public class Testes {
public static void main(String[] args) {

	Biblioteca bb = new Biblioteca();
	bb.adicionarLivro(2001, "Marcelo Fagundes" ,"Matematica Basica vol. 1", "didaticos");
	bb.adicionarLivro(2015, "Bernadete Silveira" ,"Historia da Africa", "historia");
	bb.adicionarLivro(2022, "Roberto Silveira" ,"Contos de Uma Floresta", "contos");
	
	bb.adicionarCliente("Ronnie Fraidenberges", "999.999.999-99", 2001, 12, 17);
	
System.out.println(bb.consultarLivrosDisponiveis());
	
	
	
	
}
}
