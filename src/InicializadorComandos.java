import java.util.HashMap;

public class InicializadorComandos {
	public static HashMap<String, Comando> inicializarComandos() {
		HashMap<String, Comando> comandos = new HashMap<String,Comando>();
		
		comandos.put("emp", new RealizarEmprestimo());
		comandos.put("dev", new DevolverLivro());
		comandos.put("res", new ReservarLivro());
		comandos.put("obs", new ObservarLivro());
		comandos.put("liv", new ConsultarLivro());
		comandos.put("usu", new ConsultarUsuario());
		comandos.put("ntf", new ConsultarNotificacoes());
		
		return comandos;
	}
}
