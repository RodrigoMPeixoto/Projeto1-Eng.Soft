import java.io.IOException;
import java.io.BufferedReader;

public class SistemaBiblioteca {

	public static void carregarDadosUsuarios() {
		Usuario usu1 = new AlunoGrad("123", "João da Silva");
		Usuario usu2 = new AlunoPosGrad("456", "Luiz Fernando Rodrigues");
		Usuario usu3 = new AlunoGrad("789", "Pedro Paulo");
		Usuario usu4 = new Professor("100", "Carlos Lucena");
		
		Biblioteca.obterInstancia().getUsuarios().add(usu1);
		Biblioteca.obterInstancia().getUsuarios().add(usu2);
		Biblioteca.obterInstancia().getUsuarios().add(usu3);
		Biblioteca.obterInstancia().getUsuarios().add(usu4);
	}
	
	public static void carregarDadosLivros() {
		Livro livro1 = new Livro("100", "Engenharia de Software", "AddisonWesley", "Iam Sommervile", "6ª","2000");
		Livro livro2 = new Livro("101", "UML-Guia do Usuario", "Campus", "Grady Booch, James Rumbaugh, Ivar, Jacobson", "7ª", "2000");
		Livro livro3 = new Livro("200", "Code Complete", "Microsoft Press", "Steve McConnel", "2ª", "2014");
		Livro livro4 = new Livro("201", "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", "Robert Martim", "1ª", "2002");
		Livro livro5 = new Livro("300", "Refactoring:Impoving the Desgn of Existing Code", "Addisori-Wesley Professional", "Martin Flower", "1ª", "1999");
		Livro livro6 = new Livro("301", "Software Metrics:A Rigorous and Pratical Approach", "CRC Press", "Norman Fenton, James Bieman", "3ª", "2014");
		Livro livro7 = new Livro("400", "Design Patterns:Elements of Reusable Object-Oriented Software", "AddisonWesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994");
		Livro livro8 = new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "AddisonWesley Professional", "Martin Fowler", "3ª", "2003");
		
		livro1.getExemplares().add(new Exemplar(livro1, Livro.definirCodigoExemplar(), true));
		livro1.getExemplares().add(new Exemplar(livro1, Livro.definirCodigoExemplar(), true));
		livro2.getExemplares().add(new Exemplar(livro2, Livro.definirCodigoExemplar(), true));
		livro3.getExemplares().add(new Exemplar(livro3, Livro.definirCodigoExemplar(), true));
		livro4.getExemplares().add(new Exemplar(livro4, Livro.definirCodigoExemplar(), true));
		livro5.getExemplares().add(new Exemplar(livro5, Livro.definirCodigoExemplar(), true));
		livro5.getExemplares().add(new Exemplar(livro5, Livro.definirCodigoExemplar(), true));
		livro7.getExemplares().add(new Exemplar(livro7, Livro.definirCodigoExemplar(), true));
		livro7.getExemplares().add(new Exemplar(livro7, Livro.definirCodigoExemplar(), true));
		
		Biblioteca.obterInstancia().getLivros().add(livro1);
		Biblioteca.obterInstancia().getLivros().add(livro2);
		Biblioteca.obterInstancia().getLivros().add(livro3);
		Biblioteca.obterInstancia().getLivros().add(livro4);
		Biblioteca.obterInstancia().getLivros().add(livro5);
		Biblioteca.obterInstancia().getLivros().add(livro6);
		Biblioteca.obterInstancia().getLivros().add(livro7);
		Biblioteca.obterInstancia().getLivros().add(livro8);
	}
	
	public static void main(String[] args) throws IOException {
		
		InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
		
		carregarDadosUsuarios();
		carregarDadosLivros();
		
		interfaceUsuario.fazerLoopEntrada();
	}

}
