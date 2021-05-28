public class Exemplar {
	private Livro livro;
	private String codigoExemplar; // Verificar possibilidade de definir esse codigo como variavel estatica em livro ou forma de contar.
	private boolean disponivel;
	private Emprestimo emprestimo; //Codar..
	
	
	//Construtor
	public Exemplar(Livro livro, String codigoExemplar, boolean disponivel) {
		this.livro = livro;
		this.codigoExemplar = codigoExemplar;
		this.disponivel = disponivel;
		this.setEmprestimo(null);
	}
	
	//Metodos da Classe
	
	public String toString() {
		String consulta = "";
		
		consulta += String.format("Codigo do exemplar: %s \nStatus do exemplar: %s \n", getCodigoExemplar(), isDisponivel()? "Disponivel": "Emprestado");
		if(isDisponivel() == false) {
			consulta += String.format("Usuario: %s \nData do Emprestimo: %s\nData de Devolucao: %s \n", getEmprestimo().getDono().getNomeUsuario(), getEmprestimo().getDataEmprestimoEmString(), getEmprestimo().getDataDevolucaoPrevistaEmString());
		}
		return consulta;
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

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
}
