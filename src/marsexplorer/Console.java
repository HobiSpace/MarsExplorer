package marsexplorer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Console {
	
	private static final int THRESHOLD_TO_WRITE = 100;
	private static String HOST = "localhost";
	private static int PORT = 8000;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Console console = new Console();
		console.start();
	}
	
	private Socket socket;
	private OutputStream os;
	private PrintWriter pw;
	private CommandGenerator cg;
	private StringBuilder sb;
	
	public Console() throws UnknownHostException, IOException {
		cg = new CommandGenerator();
		sb = new StringBuilder();
		createConnection();
	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String type = scanner.next();
			if (type.equals("send")) {
				sendCommands();
			} else if (type.equals("exit")){
				System.exit(0);
			} else {
				String cmd = null;
				try {
					if (type.equals(Command.AREA)) {
						cmd = cg.areaInfoCommand(
								scanner.nextFloat(), 
								scanner.nextFloat());
					} else if (type.equals(Command.INIT)) {
						cmd = cg.initInfoCommand(
								scanner.nextFloat(), 
								scanner.nextFloat(), 
								scanner.next());
					} else if (type.equals(Command.MOVE)) {
						cmd = cg.moveCommand(
								scanner.next(), 
								scanner.nextFloat());
					} else if (type.equals(Command.TURN)) {
						cmd = cg.turnCommand(
								scanner.next());
					}
				} catch (Exception e) {}
				
				
				if (cmd != null) {
					sb.append(cmd);
					System.out.println("Command saved: " + cmd);
				} else {
					System.err.println("Invalid Command.");
				}
			}
			
			if (sb.length() > THRESHOLD_TO_WRITE) { 
				sendCommands();
			}
		}
	}
	
	private void createConnection() throws UnknownHostException, IOException {
		socket = new Socket(HOST, PORT);
		os = socket.getOutputStream();  
        pw = new PrintWriter(os);
		System.out.println("Connected to Mars Explorer.");

		new Thread(new MarsMsgHandler()).start();
	}
	
	class MarsMsgHandler implements Runnable {
		public void run() {
			try {
				InputStream is = socket.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is)); 
				
				String pos;
				while ((pos = br.readLine()) != null) {
					System.out.println("Mars Explorer Returned - " + pos);
				}
			} catch (IOException e) {
				System.err.println("Fail to read from socket: " + e);
			}	
		}
	}
	
	private void sendCommands() {
		if (sb.length() == 0) {
			System.err.println("No command to send.");
			return;
		}
		try {
			sb.append("\n");
            System.out.println("Sending Commands (Size: " + sb.length() + "bytes) . . ."); 
            pw.write(sb.toString());  
            pw.flush();  
            System.out.println("Sent Successfully!");
            
            sb = new StringBuilder();
        } catch (Exception e) {
            System.err.println("Error occurs when sending: " + e);
            e.printStackTrace();
        } 
	}
}
