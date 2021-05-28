public class Professor extends Usuario{

	private int qntNotificacoes;

	public Professor(String codigoUsuario, String nomeUsuario) {
		super(codigoUsuario, nomeUsuario);
	}
	
	@Override
	public void atualizar() {
		this.qntNotificacoes++;		
	}

	@Override
	public boolean verificarRestricoesEmprestimo(String codigoLivro) {
		boolean usuarioDevedor = isUsuarioDevedor();
		boolean usuarioPossuiExemplar = usuarioPossuiExemplar(codigoLivro);
		
		if(!usuarioDevedor && !usuarioPossuiExemplar) {
			System.out.println("O usuario esta apto para realizar um emprestimo");
			return true;
		}
		
		if(usuarioDevedor) {
			System.out.println("O est� devedor de um livro");
		}
		
		if(usuarioPossuiExemplar) {
			System.out.println("O usuario ja posui um exemplar deste livro");
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
	public int getQntNotificacoes() {
		return this.qntNotificacoes;
	}
	
}
