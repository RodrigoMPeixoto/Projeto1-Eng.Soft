public class Exemplar {
	private Livro livro;
	private String codigoExemplar; // Verificar possibilidade de definir esse codigo como variavel estatica em livro ou forma de contar.
	private boolean disponivel;
	
	
	//Construtor
	public Exemplar(Livro livro, String codigoExemplar, boolean disponivel) {
		this.livro = livro;
		this.codigoExemplar = codigoExemplar;
		this.disponivel = disponivel;
	}
	
	//Metodos da Classe
	
	public String toString() {
		String consulta = "";
		
		consulta += String.format("Codigo do exemplar: %s \nStatus do exemplar: %b \n", getCodigoExemplar(), isDisponivel());
		
		// Inserir forma de consulta usuario, data de emprestimo e deovlção para retornar aqui
		return consulta;
	}
	
	public boolean pegarEmprestado(Usuario usuario) {
		//Codar...
	}
	
	public Exemplar obterExemplar() { // Talvez quem deva retornar o exemplar é o livro e não essa classe aqui
		//Codar...
	}
	
	public boolean devolverLivro() {
		//Codar...
	}
	
	//Getter e Setters
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public String getCodigoExemplar() {
		return codigoExemplar;
	}
	
	public void setCodigoExemplar(String codigoExemplar) {
		this.codigoExemplar = codigoExemplar;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}
	
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

}
