
public class ReservarLivro implements Comando {

	@Override
	public void executar(String stringComando) {
		String[] stringsComandoSplited = stringComando.split(" ");
		String codigoUsuario = stringsComandoSplited[1];
		String codigoLivro = stringsComandoSplited[2];
		Biblioteca.obterInstancia().reservarLivro(codigoUsuario, codigoLivro);
	}

}
