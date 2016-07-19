import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class AndroidSocket {
	//定义端口；
	private static final int SOCKET_PORT = 50000;
	//使用ArrayList存放所有的Socket
	public static ArrayList<Socket> sockets = new ArrayList<>();
	
	public void initMyServer(){
//		System.out.println("2222222222222SERVER IS RUNNING!!!");
		try(ServerSocket serverSocket = new ServerSocket(SOCKET_PORT)) {
			while(true){
				Socket socket = serverSocket.accept();
				//将服务端接收到的socket存放进列表
				sockets.add(socket);
				System.out.println("SERVER IS RUNNING!!!");
				new Thread(new ServerThread(socket)).start();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR　＃＃＃SERVER IS RUNNING!!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		AndroidSocket androidSocket = new AndroidSocket();
//		System.out.println("111111111111111SERVER IS RUNNING!!!");
		androidSocket.initMyServer();
	}
}
