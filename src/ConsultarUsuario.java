
public class ConsultarUsuario implements Comando {

	@Override
	public void executar(String stringComando) {
		String[] stringsComandoSplited = stringComando.split(" ");
		String codigoLivro = stringsComandoSplited[1];
		// Biblioteca.obterInstancia().consultarUsuario();

	}

}
