
public class ConsultarLivro implements Comando {

	@Override
	public void executar(String stringComando) {
		String[] stringsComandoSplited = stringComando.split(" ");
		String codigoLivro = stringsComandoSplited[1];
		Biblioteca.obterInstancia().consultarLivro(codigoLivro);
	}

}
