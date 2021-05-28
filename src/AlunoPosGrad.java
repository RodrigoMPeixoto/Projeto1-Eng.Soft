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
			System.out.println("O usuario esta apto para realizar um emprestimo");
			return true;
		}
		
		if(usuarioDevedor) {
			System.out.println("O est� devedor de um livro");
		}
		
		if(limiteEmprestimosUsuarioAlcancado) {
			System.out.println("O usuario atingiu o limite de emprestimos");
		}
		
		if(usuarioPossuiExemplar) {
			System.out.println("O usuario ja posui um exemplar deste livro");
		}
		
		return false;
	}

	private boolean limiteEmprestimosUsuarioAlcancado() {
		if(getEmprestimosCorrentes().size() == 4) {
			return true;
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
	public int getQntNotificacoes() {
		return 0;
	}

	@Override
	public void atualizar() {
		return;
	}
}
