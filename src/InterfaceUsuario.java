import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;

public class InterfaceUsuario {
	private HashMap<String, Comando> comandos;
	
	public InterfaceUsuario() {
		this.comandos = InicializadorComandos.inicializarComandos();
	}
	
	public HashMap<String, Comando> getComandos(){
		return this.comandos;
	}
	
	public String obterComandoConsole() throws IOException{
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\nOi, digite um comando:");
		return teclado.readLine();
	}
	
	public void executarComando(String stringComando) {
		try {
			Comando comando = getComandos().get(stringComando.split(" ")[0]);
			comando.executar(stringComando);
		} catch (Exception e) {
			System.out.printf("Error: " + e + "\nTente novamente.");
		}
	}
	
	public void fazerLoopEntrada() throws IOException {
		
		String stringComando = obterComandoConsole();
		while(!stringComando.split(" ")[0].equals("sai")) {
			executarComando(stringComando);
			stringComando = obterComandoConsole();
		}
	}
}
