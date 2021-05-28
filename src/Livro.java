import java.util.ArrayList;

public class Livro {

	private String codigoLivro;
	private String titulo, editora, autores; 
	private String anoPublicacao;
	private ArrayList<Exemplar> exemplares;
	private ArrayList<Observador> observadores;
	private ArrayList<Reserva> reservas;
	private ArrayList<Emprestimo> emprestimos;
	public static int numExemplares = 0; 
	
	//Construtor
	public Livro(String codigoLivro, String titulo, String editora, String autores, String anoPublicacao) {
		this.codigoLivro = codigoLivro;
		this.titulo = titulo;
		this.editora = editora;
		this.autores = autores;
		this.anoPublicacao = anoPublicacao;
		
		setExemplares(new ArrayList<Exemplar>()); //Adicionar aqui o primeiro exemplar
		setObservadores(new ArrayList<Observador>());
		setReservas(new ArrayList<Reserva>());
		setEmprestimos(new ArrayList<Emprestimo>());
		
		getExemplares().add(new Exemplar(this, definirCodigoExemplar(), true));
	}
	
	public String definirCodigoExemplar(){
		Livro.numExemplares ++;
		
		return String.valueOf(numExemplares);
	}
	
	//Metodos da Classe
	
	//Retorna o primeiro exemplar disponivel do livro
	public Exemplar buscarExemplarDisponivel() {
		for(int i = 0; i<exemplares.size(); i++) {
			if(exemplares.get(i).isDisponivel() == true) {
				return exemplares.get(i);
			}
		}
		return null;
	}
	
	//Retornar a quantidade de exemplares disponiveis
	public int qntExemplaresDisponiveis() {
		int cont = 0;
		for(int i = 0; i<exemplares.size(); i++) {
			if(exemplares.get(i).isDisponivel() == true) {
				cont += 1;
			}
		}
		return cont;
	}
	
	//Busca a reserva de um usuario passando seu codigo e retornando sua posição na lista
	public int buscarPosicao(String codigoUsuario) {
		for(int i = 0; i<reservas.size(); i++) {
			if(reservas.get(i).getUsuario().getCodigoUsuario()== codigoUsuario) {
				return i+1;
			}
		}
		return -1;
	}
	
	//Verifica se existe livro que o usuario possa pegar e retorna ele.
	public Exemplar verificarDisponibilidade(String codigoUsuario, boolean prioridade) { 	
		if(prioridade == true) {//Para prioridade não importa as reservas
			return buscarExemplarDisponivel();
		}
		else {
			if(qntExemplaresDisponiveis() >= reservas.size()) {//Se tiver mais livros que reservas
				return buscarExemplarDisponivel();
			}else {
				int posicaoUsuario = buscarPosicao(codigoUsuario);
				if ( posicaoUsuario >= 0 && posicaoUsuario <= qntExemplaresDisponiveis()) {//Se a reserva dele for suficiente para pegar um livro
					return buscarExemplarDisponivel();
				}
			}
		}
		
		return null; //Nenhuma das etapas acima cumpridas retorna nulo indicando que não existe livro disponiel para o usuario
	}
	
	public void adicionarReserva(Livro livro, Usuario usuario) {
		getReservas().add(new Reserva(livro, usuario));
		if(reservas.size() == 3) {
			notificarObservador();
			System.out.println("Notificando observadores");
			return;
		}
	}
	
	//Remove uma reserva passando o codigo do usuario
	public void removerReserva(String codigoUsuario) {
		//Deletar a instancia reserva de dentro do array e da memoria? -----------------------
		for(int i = 0; i<reservas.size(); i++) {
			if(reservas.get(i).getUsuario().getCodigoUsuario()== codigoUsuario) {
				reservas.remove(i);
			}
		}
	}
	
	public void pegarEmprestado(Exemplar exemplar, Emprestimo emprestimo) {
		//Define o exemplar como emprestado
		for(int i = 0; i<exemplares.size(); i++) {
			if(exemplares.get(i) == exemplar) {
				exemplares.get(i).setDisponivel(false);
				exemplares.get(i).setEmprestimo(emprestimo);
			}
		}
		
		return;
	}
	
	/*
	public Exemplar obterExemplar() {
		Exemplar exemplar;
		
		//Codar...
		
		return null;
	}
	*/
	
	public boolean devolverLivro(String codigoUsuario) {
		boolean realizado = false;
		
		//Remover o livro de emprestimos
		for(int i = 0; i<emprestimos.size(); i++) {
			if(emprestimos.get(i).getDono().getCodigoUsuario().equals(codigoUsuario)) {
				Exemplar exemplar = emprestimos.get(i).getExemplar();
				for(int j = 0; j<exemplares.size(); j++) {
					if(exemplares.get(j) == exemplar) {
						exemplares.get(j).setDisponivel(true);
						exemplares.get(j).setEmprestimo(null);
					}
				}
				getEmprestimos().remove(i);
				//Inserir uma mensagem aqui caso não encontre o emprestimo no array?
				realizado = true;
			}
		}
		
		return realizado;
	}
	 
	public void registrarObservador(Usuario usuario) {
		getObservadores().add(usuario); // verificar se essa situação age conforme o previsto
		System.out.println("Observador registrado com sucesso");
		return;
	}
	
	public void removerObservador(Usuario usuario) { // Verificar possibilidade de alterar o retorno para boolean
		for(int i = 0; i<observadores.size(); i++) {
			if(observadores.get(i) == usuario) {
				getObservadores().remove(i);
				System.out.println("Observador removido com sucesso");
				return;
			}
		}
		System.out.println("Esse observador não está registrado");
		return;
	}
	
	public void notificarObservador() {
		if (observadores.size() == 0) {
			System.out.println("N�o existem observadores registrados");
			return;
		}
		
		for(int i=0; i<observadores.size(); i++) {
			observadores.get(i).atualizar();
		}
		System.out.println("Observadores notificados com sucesso");
		return;
	}
	
	public String exibirReservas() {
		String reserva = "";
		for(int i=0; i<reservas.size(); i++) {
			reserva += reservas.get(i).getUsuario().getNomeUsuario();
      reserva += "\n"; 
		}
		
		return reserva;
	}
	
	public String exibirExemplares() {
		String exemplar = "";
		for(int i=0; i<exemplares.size(); i++) {
			exemplar += exemplares.get(i).toString(); 
		}
		
		return exemplar;
	}
	
	public String toString() {
		String consulta = "";
		
		consulta += String.format("Título: %s \nQuantidade de reservas: %s \n", this.titulo, reservas.size());
		
		if(reservas.size() > 0) {
			consulta += String.format("%s\n", exibirReservas());
		}
		consulta += String.format("%s\n", exibirExemplares());
		
		return consulta;
	}
	
	//Getter e Setter
	public String getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public String getAutores() {
		return autores;
	}
	
	public void setAutores(String autores) {
		this.autores = autores;
	}
	
	public String getAnoPublicacao() {
		return anoPublicacao;
	}
	
	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public ArrayList<Exemplar> getExemplares() {
		return exemplares;
	}
	
	public void setExemplares(ArrayList<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	
	public ArrayList<Observador> getObservadores() {
		return observadores;
	}
	
	public void setObservadores(ArrayList<Observador> observadores) {
		this.observadores = observadores;
	}
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
}
