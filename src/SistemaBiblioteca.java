import java.io.IOException;
import java.io.BufferedReader;

public class SistemaBiblioteca {

	public static void main(String[] args) throws IOException {
		//String path = "..usuarios.txt";
		//LeitorArquivo.leitor(path);
		
		InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
		
		
		Livro livro = new Livro("100", "O Livro", "A editora", "Os autores", "Ontem");
		livro.getExemplares().add(new Exemplar(livro, livro.definirCodigoExemplar(), true));
		
		Usuario usuario = new AlunoGrad("01", "Rodrigo");
		Usuario usuario2 = new Professor("02", "Mario");
		Usuario usuario3 = new AlunoPosGrad("03", "Vitor");
		
		Biblioteca.obterInstancia();
		Biblioteca.obterInstancia().getLivros().add(livro);
		Biblioteca.obterInstancia().getUsuarios().add(usuario);
		Biblioteca.obterInstancia().getUsuarios().add(usuario2);
		Biblioteca.obterInstancia().getUsuarios().add(usuario3);
		
		
		interfaceUsuario.fazerLoopEntrada();
		
		
	}

}
