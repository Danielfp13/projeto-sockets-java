package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	// cria servidor de aplicação
	private void criarSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	// espera um pedido de conexao outro processo
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	private void fechaSocket(Socket socket) throws IOException {
		socket.close();
	}

	// cliente-----Socket---------Servidor
	private void tratarConexao(Socket socket) throws IOException {
		// cria stream de entrada e saida e trata conversação entre cliente e servidor(tratar protocolo)
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			// cliente envia uma mensagem e servidor envia mensagem de volta
			String mensagem = input.readUTF();
			System.out.println("Mensagem recebida. " + mensagem);
			output.writeUTF("Eai blz cliente. ");
			output.flush();

			input.close();
			output.close();

		} catch (IOException e) {
			// tratamento das falhas de conexão
			System.out.println("problema no tratamento de falhas da conexão com o cliente " + socket.getInetAddress());
			System.out.println("Erro " + e.getMessage());
		} finally {
			fechaSocket(socket);
		}
	}

	public static void main(String[] args) {

		try {
			Server server = new Server();
			System.out.println("Aguardando conexão");
			server.criarSocket(5555);
			Socket socket = server.esperaConexao();// protocolo
			System.out.println("Cliente conectado");
			server.tratarConexao(socket);

		} catch (Exception e) {

		}

	}
}
