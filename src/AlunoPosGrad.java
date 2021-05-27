import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class AlunoPosGrad extends Usuario {
	
	public AlunoPosGrad(String codigoUsuario, String nomeUsuario) {
		super(codigoUsuario, nomeUsuario);
	}

	@Override
	public boolean verificarRestricoesEmprestimo(String codigoLivro) {
		boolean usuarioDevedor = isUsuarioDevedor();
		boolean limiteEmprestimosUsuarioAlcancado = limiteEmprestimosUsuarioAlcancado();
		boolean usuarioPossuiExemplar = usuarioPossuiExemplar(codigoLivro);
		
		if(!usuarioDevedor 
			&& !limiteEmprestimosUsuarioAlcancado
			&& !usuarioPossuiExemplar
		) {
			System.out.println("O usuário está apto para realizar um empréstimo");
			return true;
		}
		return false;
	}
	
	private boolean usuarioPossuiExemplar(String codigoLivro) {
		for(int i=0; i<getEmprestimosCorrentes().size(); i++) {
			Emprestimo e = getEmprestimosCorrentes().get(i);
			if(e.getExemplar().getLivro().getCodigoLivro().equals(codigoLivro)) {
				return true;
			}
		}
		return false;
	}

	private boolean limiteEmprestimosUsuarioAlcancado() {
		if(getEmprestimosCorrentes().size() == 4) {
			return true;
		}
		return false;
	}

	private boolean isUsuarioDevedor() {
		for(int i=0; i<getEmprestimosCorrentes().size(); i++) {
			Emprestimo e = getEmprestimosCorrentes().get(i);
			long diasEntreHojeEmprestimo = ChronoUnit.DAYS.between(Calendar.getInstance().toInstant(),e.getDataEmprestimo().toInstant());
			if(diasEntreHojeEmprestimo > 4) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean verificarPrioridade() {
		return false;
	}
	
	@Override
	public int numeroDiasEmprestimo() {
		return 4;
	}
	
	@Override
	public int getQtdNotificacoes() {
		return 0;
	}

	@Override
	public void atualizar() {
		return;
	}
}
