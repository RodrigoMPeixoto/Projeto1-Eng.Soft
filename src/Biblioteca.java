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
			if(usuarios.get(i).getCodigoUsuario().equals(codigoUsuario)) {
				return usuarios.get(i);
			}
		}
		
		return null;
	}
	
	public Livro buscarLivro(String codigoLivro) {
		for(int i = 0; i<livros.size(); i++) {
			if(livros.get(i).getCodigoLivro().equals(codigoLivro)) {
				return livros.get(i);
			}
		}
		
		return null;
	}
	
	public void pegarEmprestado(String codigoUsuario, String codigoLivro) {
		boolean prioridade;
		
		//Busca na lista de usuario e verificar se ele cumpre as restricoes
		Usuario usuario = buscarUsuario(codigoUsuario);
		if(usuario == null) { // Verifica se o usuario existe
			System.out.println("Usuario nao encontrado");
			return;
		}else {
			boolean usuarioLiberado = usuario.verificarRestricoesEmprestimo(codigoLivro);
			prioridade = usuario.verificarPrioridade();
			if(!usuarioLiberado) {
				System.out.println("Nao foi possivel realizar o emprestimo");
				return;
			}
		}	
		
		//Verificar o livro
		Livro livro = buscarLivro(codigoLivro);
		if(livro == null) { // Verifica se o livro existe
			System.out.println("Livro nao encontrado");
			return;
		}else {
			Exemplar exemplar = livro.verificarDisponibilidade(codigoUsuario, prioridade);
			if(exemplar == null) {
				System.out.println("Nao existem livros disponives no momento");
				return;
			}else {
				Emprestimo emprestimo = new Emprestimo(usuario, exemplar);
				livro.getEmprestimos().add(emprestimo);
				usuario.getEmprestimosCorrentes().add(emprestimo);
				livro.pegarEmprestado(exemplar, emprestimo);
				livro.removerReserva(codigoUsuario);
				usuario.removerReserva(codigoLivro);
			}
		}
		
		System.out.printf("O livro %s foi emprestado para %s com sucesso\n", livro.getTitulo(),usuario.getNomeUsuario());
	}
	
	public void devolverLivro(String codigoUsuario, String codigoLivro) {
		Livro livro = buscarLivro(codigoLivro);
		Usuario usuario = buscarUsuario(codigoUsuario);
		livro.devolverLivro(codigoUsuario);
		usuario.devolverLivro(codigoLivro);
		//Verificar possibilidade de deletar a classe emprestimo referente ao livro
	}
	
	public void reservarLivro(String codigoUsuario, String codigoLivro) {
		Livro livro = buscarLivro(codigoLivro);
		Usuario usuario = buscarUsuario(codigoUsuario);
		if(usuario.verificarRestricoesReserva(codigoLivro)) {
			Reserva reserva = new Reserva(livro, usuario);
			livro.adicionarReserva(reserva);
			usuario.getReservas().add(reserva);
			System.out.println("Reserva realizada com sucesso");
			return; 
		}
		System.out.println("Nao foi possivel realizar a reserva para esse usuario");
		return;
	}
	
	public void observarLivro(String codigoUsuario, String codigoLivro) {
		Livro livro = buscarLivro(codigoLivro);
		Usuario usuario = buscarUsuario(codigoUsuario);
		livro.registrarObservador(usuario);
	}
	
	public void pararDeObservarLivro(String codigoUsuario, String codigoLivro) {
		Livro livro = buscarLivro(codigoLivro);
		Usuario usuario = buscarUsuario(codigoUsuario);
		livro.removerObservador(usuario);
		return;
	}
	
	public void consultarLivro(String codigoLivro) {
		Livro livro = buscarLivro(codigoLivro);
		String infoLivro = livro.toString();
		System.out.printf("Resultado da Consulta:\n%s", infoLivro);
		return;
	}
	
	public void consultarUsuario(String codigoUsuario) {
		Usuario usuario = buscarUsuario(codigoUsuario);
		usuario.consultarUsuario();
		return;
	}
	
	public void consultarQntNotificacoes(String codigoUsuario) {
		Usuario usuario = buscarUsuario(codigoUsuario);
		System.out.printf("O usuario recebeu %d notificacoes.", usuario.getQntNotificacoes());
		return;
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
