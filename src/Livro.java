import java.util.ArrayList;

public class Livro {

	private String codigoLivro;
	private String titulo, editora, autores; 
	private String anoPublicacao;
	private ArrayList<Exemplar> exemplares;
	private ArrayList<Observador> observadores;
	private ArrayList<Reserva> reservas;
	private ArrayList<Emprestimo> emprestimos;
	
	//Construtor
	public Livro(String codigoLivro, String titulo, String editora, String autores, String anoPublicacao) {
		this.codigoLivro = codigoLivro;
		this.titulo = titulo;
		this.editora = editora;
		this.autores = autores;
		this.anoPublicacao = anoPublicacao;
		
		setExemplares(new ArrayList<Exemplar>());
		setObservadores(new ArrayList<Observador>());
		setReservas(new ArrayList<Reserva>());
		setEmprestimos(new ArrayList<Emprestimo>());
		
		//Chamar o primeiro exemplar do livro ao ser instanciado
		Exemplar();
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
	}
	
	//Verifica se existe livro que o usuario possa pegar e retorna ele.
	public Exemplar verificarDisponibilidade(String codigoUsuario, boolean prioridade) { 	
		if(prioridade == true) {//Para prioridade não importa as reservas
			return buscarExemplarDisponivel();
		}
		else {
			if(qntExemplaresDisponiveis >= reservas.size()) {//Se tiver mais livros que reservas
				return buscarExemplarDisponivel();
			}else if (buscaPosicao(codigoUsuario) <= qntExemplaresDisponiveis) {//Se a reserva dele for suficiente para pegar um livro
				return buscarExemplarDisponivel();
			}
		}
		
		return null; //Nenhuma das etapas acima cumpridas retorna nulo indicando que não existe livro disponiel para o usuario
	}
	
	public boolean pegarEmprestado() {
		boolean disponivel = true;
		
		//Codar...
		
		return disponivel;
	}
	
	public Exemplar obterExemplar() {
		Exemplar exemplar;
		
		//Codar...
		
		return exemplar;
	}
	
	public bool devolverLivro() {
		
		//Codar
	}
	
	public void registrarObservaodr() {
		
		//Codar...
	}
	
	public void removerObservador() { // Verificar possibilidade de alterar o retorno para boolean
		
		//Codar
	}
	
	public void notificarObservador() {
		//Codar..
	}
	
	public void atualizarQntReservas(int qntReservas) {
		//Codar
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