import java.util.ArrayList;

public class Biblioteca {
	private ArrayList<Livro> livros;
	private ArrayList<Usuario> usuarios;
	
	
	//Utilizando o Singleton o construtor passa a ser privado
	private Biblioteca() {
		setLivros(new ArrayList<Livro>()); 
		setUsuarios(new ArrayList<Usuario>());
	}
	
	private static Biblioteca instancia;
	
	public static Biblioteca obterInstancia() {
		if (instancia == null) {
			instancia = new Biblioteca();
		}
		return instancia;
	}

	
	//Metodos da classe
	public Usuario buscarUsuario(String codigoUsuario) {
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getCodigoUsuario() == codigoUsuario) {
				return usuarios.get(i);
			}
		}
		
		return null;
	}
	
	public Livro buscarLivro(String codigoLivro) {
		for(int i = 0; i<livros.size(); i++) {
			if(livros.get(i).getCodigoLivro() == codigoLivro) {
				return livros.get(i);
			}
		}
		
		return null;
	}
	
	public void pegarEmprestado(String codigoUsuario, String codigoLivro) {
		boolean prioridade;
		
		//Busca na lista de usuario e verificar se ele cumpre as restrições
		Usuario usuario = buscarUsuario(codigoUsuario);
		if(usuario == null) { // Verifica se o usuario existe
			System.out.println("Usuario não encontrado");
			return;
		}else {
			boolean usuarioPendente = usuario.verificarRestricoesEmprestimos(codigoLivro);
			prioridade = usuario.verificarPioridade();
		}	
		
		//Verificar o livro
		Livro livro = buscarLivro(codigoLivro);
		if(livro == null) { // Verifica se o livro existe
			System.out.println("Livro não encontrado");
			return;
		}else {
			Exemplar exemplar = livro.verificarDisponibilidade(codigoUsuario, prioridade);
			if(exemplar == null) {
				System.out.println("Não existem livros disponíves no momento");
				return;
			}else {
				boolean pegarLivro = livro.pegarEmprestado(codigoUsuario);
				if(pegarLivro == false) {
					System.out.println("Operação não realizada"); // Melhorar isso
					return;
				}else {
					System.out.println("Operação Realizada com sucesso"); // Melhorar isso
				}
			}
		}
		
		//Pegou emprestado usando a reserva removemos a reserva e efetivamos o emprestimo
		
		//Deve ser retornada uma mensagem de sucesso ou insucesso
		return mensagem;
		
	}
	
	public void devolverLivro() {
		
	}
	
	public void reservarLivro() {
		
	}
	
	public void observarLivro() {
		
	}
	
	public void consultarLivro() {
		
	}
	
	public void consultarUsuario() {
		
	}
	
	public void consultarQntNotificacoes() {
		
	}
	
	//Getters e Setters
	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
