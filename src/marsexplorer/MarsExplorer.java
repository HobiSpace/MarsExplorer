package marsexplorer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MarsExplorer {
	
	private static int PORT = 8000;
	
	public static void main(String[] args) throws IOException {
		MarsExplorer explorer = new MarsExplorer();
		explorer.start();
	}
	
	private CommandExecutor cc;
	private Socket socket;
	
	public MarsExplorer() {
		cc = new CommandExecutor();
	}
	
	public void start() throws IOException {		
		ServerSocket serverSocket = new ServerSocket(PORT); 
		while (true) {
			socket = serverSocket.accept();
			System.out.println("Connected to Console.");
			
			InputStream is = socket.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
            
            OutputStream os = socket.getOutputStream();  
            PrintWriter pw = new PrintWriter(os);
            
			try {
				String commandBatch;
				while ((commandBatch = br.readLine()) != null) {
					for (String cmd : commandBatch.split(";")) {
						System.out.println("Command: " + cmd);
						cc.executeCommand(cmd);
					}
					
					pw.write(cc.getPosition() + "\n");
			        pw.flush();
				}
				
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
