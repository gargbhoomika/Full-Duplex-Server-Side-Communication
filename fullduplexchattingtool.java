package fullduplex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class receiver extends Thread
{
	Socket s1 = null;
	public receiver(Socket s) 
	{
		s1=s;	
	}

	public void run()
	{
		try
		{
			DataInputStream dis = new DataInputStream(s1.getInputStream());
			while(true)
			{
				String str = dis.readUTF();
				System.out.println("                             Friend: "+str);
			}
		}
		catch(Exception e) {System.out.println("Client Down"); }
	}
}

public class fullduplexchattingtool 
{
	

	private static Scanner sc;
	private static ServerSocket ss;

	public static void main(String[] args) 
	{
		try
		{
			sc = new Scanner(System.in);
			System.out.println("Enter the same port number as client: ");
			int port = sc.nextInt();
			ss = new ServerSocket(port);
			Socket s = ss.accept();
			receiver thd1 = new receiver(s);
			thd1.start();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while(true)
			{
			System.out.println("You: ");
			String x = sc.nextLine();
			dos.writeUTF(x);
			}
		}
		catch(Exception e) {System.out.println("Main down");}

	}

}