import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public abstract class Usuario implements Observador{
	private String codigoUsuario;
	private String nomeUsuario;
	private ArrayList<Emprestimo> emprestimosCorrentes;
	private ArrayList<String> emprestimosPassados;
	private ArrayList<Reserva> reservas;
	
	public Usuario(String codigoUsuario, String nomeUsuario) {
		this.codigoUsuario = codigoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emprestimosCorrentes = new ArrayList<Emprestimo>();
		this.emprestimosPassados = new ArrayList<String>();
		this.reservas = new ArrayList<Reserva>();
	}
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public ArrayList<Emprestimo> getEmprestimosCorrentes() {
		return emprestimosCorrentes;
	}

	public ArrayList<String> getEmprestimosPassados() {
		return emprestimosPassados;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public abstract boolean verificarRestricoesEmprestimo(String codigoLivro);
	
	public boolean usuarioPossuiExemplar(String codigoLivro) {
		for(int i=0; i<getEmprestimosCorrentes().size(); i++) {
			Emprestimo e = getEmprestimosCorrentes().get(i);
			if(e.getExemplar().getLivro().getCodigoLivro().equals(codigoLivro)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUsuarioDevedor() {
		for(int i=0; i<getEmprestimosCorrentes().size(); i++) {
			Emprestimo e = getEmprestimosCorrentes().get(i);
			long diasEntreHojeEmprestimo = ChronoUnit.DAYS.between(e.getDataEmprestimo().toInstant(),Calendar.getInstance().toInstant());
			if(diasEntreHojeEmprestimo >= numeroDiasEmprestimo()) {
				return true;
			}
		}
		return false;
	}
	
	public abstract boolean verificarPrioridade();
	
	public boolean removerReserva(String codigoLivro) {
		Iterator<Reserva> it = getReservas().iterator();
		
		while(it.hasNext()) {
			Reserva r = it.next();
			if(r.getLivro().getCodigoLivro().equals(codigoLivro)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean devolverLivro(String codigoLivro) {
		Iterator<Emprestimo> it = getEmprestimosCorrentes().iterator();
		
		while(it.hasNext()) {
			Emprestimo e = it.next();
			if(e.getExemplar().getLivro().getCodigoLivro().equals(codigoLivro)) {			
				getEmprestimosPassados().add(e.transformarEmprestimoCorrenteEmPassado());
				System.out.println("Livro movido para o historico do usuario");
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public abstract int numeroDiasEmprestimo();
	
	public boolean verificarRestricoesReserva(String codigoLivro) {
		boolean limiteReservasAtingido = limiteReservasAtingido();
		boolean existeReservaUsuario = usuarioPossuiReserva(codigoLivro);
		boolean existeEmprestimo = usuarioPossuiExemplar(codigoLivro);
		
		
		if(!limiteReservasAtingido &&
			!existeReservaUsuario && !existeEmprestimo) {
			return true;
		}
		
		if(limiteReservasAtingido) {
			System.out.println("O usuario atingiu o limite de reservas");
		}
		
		if(existeReservaUsuario) {
			System.out.println("O usuario ja possui uma reserva deste livro");
		}
		
		if(existeEmprestimo) {
			System.out.println("O usuario ja possui um exemplar deste livro emprestado");
		}
		
		return false;
	}

	private boolean usuarioPossuiReserva(String codigoLivro) {
		Iterator<Reserva> it = getReservas().iterator();
		
		while(it.hasNext()) {
			Reserva r = it.next();
			if(r.getLivro().getCodigoLivro().equals(codigoLivro)) {
				return true;
			}
		}
		return false;
	}

	private boolean limiteReservasAtingido() {
		if(getReservas().size() == 3) {
			return true;
		}
		return false;
	}
	
	public void consultarUsuario() {
		Iterator<Emprestimo> it = getEmprestimosCorrentes().iterator();
		
		System.out.println("# Emprestimos Correntes");
		
		while(it.hasNext()) {
			Emprestimo emprestimoCorrente = it.next();
			System.out.println(emprestimoCorrente.toString());
			System.out.println();
		}
		
		System.out.println("# Emprestimos Passados");
		
		Iterator<String> it2 = getEmprestimosPassados().iterator();
		
		while(it2.hasNext()) {
			String emprestimoPassado = it2.next();
			System.out.println(emprestimoPassado);
			System.out.println();
		}
		
		Iterator<Reserva> it3 = getReservas().iterator();
		
		System.out.println("# Reservas");
		
		while(it3.hasNext()) {
			Reserva reserva = it3.next();
			System.out.println(reserva.toString());
			System.out.println();
		}
	}
	
	public abstract int getQntNotificacoes();
}
