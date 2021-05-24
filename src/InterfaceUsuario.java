import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;

public class InterfaceUsuario {
	private HashMap<String, Comando> comandos;
	
	public InterfaceUsuario() {
		InicializadorComandos.inicializarComandos();
	}
	
	public HashMap<String, Comando> getComandos(){
		return this.comandos;
	}
	
	public String obterComandoConsole() throws IOException{
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\nOlá, digite um comando:");
		return teclado.readLine();
	}
	
	public void executarComando(String stringComando) {
		Comando comando = getComandos().get(stringComando.split(" ")[0]);
		comando.executar(stringComando);
	}
	
	public void fazerLoopEntrada() throws IOException {
		
		String stringComando = obterComandoConsole();
		while(!stringComando.split(" ")[0].equals("sair")) {
			executarComando(stringComando);
			stringComando = obterComandoConsole();
		}
	}
}
