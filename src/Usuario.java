import java.util.ArrayList;
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
	
	public boolean verificarEmprestimoCorrenteLivro(String codigoLivro) {
		Iterator<Emprestimo> it = getEmprestimosCorrentes().iterator();
		
		while(it.hasNext()) {
			Emprestimo e = it.next();
			if(e.getExemplar().getLivro().getCodigoLivro().equals(codigoLivro)) {
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
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public abstract int numeroDiasEmprestimo();
	
	public boolean verificarRestricoesReserva(String codigoLivro) {
		boolean limiteReservasAtingido = limiteReservasAtingido();
		boolean existeReservaUsuario = existeReservaUsuario(codigoLivro);
		
		if(!limiteReservasAtingido &&
			!existeReservaUsuario) {
			return true;
		}
		
		return false;
	}

	private boolean existeReservaUsuario(String codigoLivro) {
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
	
	public void consultaUsuario() {
		Iterator<Emprestimo> it = getEmprestimosCorrentes().iterator();
		
		System.out.println("# Emprestimos Correntes");
		
		while(it.hasNext()) {
			Emprestimo emprestimoCorrente = it.next();
			System.out.println(emprestimoCorrente.toString());
			System.out.println();
		}
		
		System.out.println("# Emprestimos Passados");
		
		Iterator<String> it2 = getEmprestimosPassados().iterator();
		
		while(it.hasNext()) {
			String emprestimoPassado = it2.next();
			System.out.println(emprestimoPassado);
			System.out.println();
		}
		
		Iterator<Reserva> it3 = getReservas().iterator();
		
		System.out.println("# Reservas");
		
		while(it.hasNext()) {
			Reserva reserva = it3.next();
			System.out.println(reserva.toString());
			System.out.println();
		}
	}
	
	public abstract int getQtdNotificacoes();
}
