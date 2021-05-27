import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Professor extends Usuario{

	private int qtdNotificacoes;

	public Professor(String codigoUsuario, String nomeUsuario) {
		super(codigoUsuario, nomeUsuario);
	}
	
	@Override
	public void atualizar() {
		this.qtdNotificacoes++;		
	}

	@Override
	public boolean verificarRestricoesEmprestimo(String codigoLivro) {
		boolean usuarioDevedor = isUsuarioDevedor();
		
		if(!usuarioDevedor) {
			System.out.println("O usuário está apto para realizar um empréstimo");
			return true;
		}
		return false;
	}

	private boolean isUsuarioDevedor() {
		for(int i=0; i<getEmprestimosCorrentes().size(); i++) {
			Emprestimo e = getEmprestimosCorrentes().get(i);
			long diasEntreHojeEmprestimo = ChronoUnit.DAYS.between(Calendar.getInstance().toInstant(),e.getDataEmprestimo().toInstant());
			if(diasEntreHojeEmprestimo > 7) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean verificarPrioridade() {
		return true;
	}
	
	@Override
	public int numeroDiasEmprestimo() {
		return 7;
	}
	
	@Override
	public int getQtdNotificacoes() {
		return this.qtdNotificacoes;
	}
	
}
