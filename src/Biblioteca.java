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
				System.out.println(usuarios.get(i).getCodigoUsuario());
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
			boolean usuarioPendente = usuario.verificarRestricoesEmprestimo(codigoLivro);
			prioridade = usuario.verificarPrioridade();
			if(usuarioPendente == true) {
				System.out.println("Usuario possui pend�ncias");
				return;
			}
		}	
		
		//Verificar o livro
		Livro livro = buscarLivro(codigoLivro);
		if(livro == null) { // Verifica se o livro existe
			System.out.println("Livro n�o encontrado");
			return;
		}else {
			Exemplar exemplar = livro.verificarDisponibilidade(codigoUsuario, prioridade);
			if(exemplar == null) {
				System.out.println("N�o existem livros dispon�ves no momento");
				return;
			}else {
				Emprestimo emprestimo = new Emprestimo(usuario, exemplar);
				livro.getEmprestimos().add(emprestimo);
				usuario.getEmprestimosCorrentes().add(emprestimo);
				livro.pegarEmprestado(exemplar, emprestimo);
				livro.removerReserva(codigoUsuario); // Verificar possibilidade de remover a reserva dentro do metodo pegar emprestado
				usuario.removerReserva(codigoLivro); //Possibilidade de melhorar essa remoção de reserva
			}
		}
		
		
		//Deve ser retornada uma mensagem de sucesso ou insucesso
		System.out.println("Emprestimo realizado com sucesso");
		
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
			//Utilizar a biblioteca de tempo aqui
			//String dataEmprestimo =
			livro.adicionarReserva(livro,usuario);
			System.out.println("Reserva realizada com sucesso");
			return; 
		}
		System.out.println("Não foi possível realizar a reserva para esse usuário");
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
		System.out.println(usuario.toString());
		return;
	}
	
	public void consultarQntNotificacoes(String codigoUsuario) {
		Usuario usuario = buscarUsuario(codigoUsuario);
		System.out.printf("O usuario recebeu %d.", usuario.getQtdNotificacoes());
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
