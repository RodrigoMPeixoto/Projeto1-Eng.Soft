import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Emprestimo {
	private Usuario dono;
	private Exemplar exemplar;
	private Calendar dataEmprestimo;
	
	public Emprestimo(Usuario dono, Exemplar exemplar) {
		this.dono = dono;
		this.exemplar = exemplar;
		this.dataEmprestimo = Calendar.getInstance();
	}
	
	public Usuario getDono() {
		return dono;
	}
	public Exemplar getExemplar() {
		return exemplar;
	}
	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public String getDataEmprestimoEmString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(getDataEmprestimo().getTime());
	}
	
	public String getDataDevolucaoPrevistaEmString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar dataDevolucaoPrevista = getDataEmprestimo();
		dataDevolucaoPrevista.add(dataDevolucaoPrevista.DATE, dono.numeroDiasEmprestimo());
		String dataDevolucaoPrevistaEmString = formatter.format(dataDevolucaoPrevista.getTime());
		dataDevolucaoPrevista.add(dataDevolucaoPrevista.DATE, -dono.numeroDiasEmprestimo());
		return dataDevolucaoPrevistaEmString;
	}
	
	public String transformarEmprestimoCorrenteEmPassado() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		String emprestimoPassado = String.format(""
				+ "Titulo: %s\n"
				+ "Data do Emprestimo: %s\n"
				+ "Status do Emprestimo: Finalizado\n"
				+ "Data da Devolucao: %s", 
				getExemplar().getLivro().getTitulo(),
				getDataEmprestimoEmString(),
				formatter.format(Calendar.getInstance().getTime()));
		System.out.printf("Data da Devolucao: %s\n" ,formatter.format(Calendar.getInstance().getTime()));
		
		return emprestimoPassado;
	}
	
	public String toString() {
		String emprestimoCorrente = String.format(""
				+ "Titulo: %s\n"
				+ "Data do Emprestimo: %s\n"
				+ "Status do Emprestimo: Em curso\n"
				+ "Data da Devolucao Prevista: %s", 
				getExemplar().getLivro().getTitulo(),
				getDataEmprestimoEmString(),
				getDataDevolucaoPrevistaEmString());
		
		return emprestimoCorrente;
	}
}
