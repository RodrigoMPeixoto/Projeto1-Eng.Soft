
public class ConsultarNotificacoes implements Comando {

	@Override
	public void executar(String stringComando) {
		String[] stringsComandoSplited = stringComando.split(" ");
		String codigoUsuario = stringsComandoSplited[1];
		Biblioteca.obterInstancia().consultarQntNotificacoes(codigoUsuario);
	}

}
