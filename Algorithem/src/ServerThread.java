import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/*处理服务端接收客户端发送过来的消息的类*/
public class ServerThread implements Runnable {
	private Socket socket = null;
	//线程所处理的socket对应的输入流
	private BufferedReader bufferedReader = null;
	
	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String content = null;
			while((content = bufferedReader.readLine()) != null){
				for(Socket socket:AndroidSocket.sockets){
					PrintStream printStream = new PrintStream(socket.getOutputStream());
					printStream.println(packMessage(content));
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * Function 对广播的数据进行包装
	 */
	private String packMessage(String content) {
		String result = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		if(content.startsWith("USER_ONE")){
			String message = content.substring(8);
			result = "\n" +"往事如风" + simpleDateFormat.format(new Date()) + "\n" + message;
		}
		if(content.startsWith("USER_TWO")){
			String message = content.substring(8);
			result = "\n" +"依旧淡然" + simpleDateFormat.format(new Date()) + "\n" + message;
		}
		return result;
	}
	
}

