package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		//estabelecer conexão
		try {
			//cria conexão com o servidor
			
			System.out.println("Estabelecendo conexão");
			Socket socket = new Socket("localhost",5555);
			System.out.println("Conexão estabelecida");
			
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			System.out.println("Enviando mensagem");
			
			String mensagem = "Eai servidor!!! ";
			output.writeUTF(mensagem);
			output.flush();
			
			System.out.println("Mensagem " + mensagem + " Enviada.");
			
			mensagem = input.readUTF();
			System.out.println("Resposta do servidor. " + mensagem);
			
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		//trocar mensagens
	}
}
