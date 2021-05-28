import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reserva {
	private Livro livro;
	private Usuario usuario;
	private Calendar dataReserva;
	
	public Reserva(Livro livro, Usuario usuario) {
		this.livro = livro;
		this.usuario = usuario;
		this.dataReserva = Calendar.getInstance();
	}

	public Livro getLivro() {
		return livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Calendar getDataReserva() {
		return dataReserva;
	}
	
	public String getDataReservaEmString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(getDataReserva().getTime());
	}
	
	public String toString() {
		
		String emprestimoCorrente = String.format(""
				+ "Titulo: %s\n"
				+ "Data da Reserva: %s\n",
				getLivro().getTitulo(),
				getDataReservaEmString());
		
		return emprestimoCorrente;
	}

}
