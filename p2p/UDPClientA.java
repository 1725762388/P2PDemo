package p2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class UDPClientA {

	public static void main(String[] args) throws IOException {
	
		System.out.println("�ͻ���A�Ѿ�����");
		/*************�ͻ���A����������������*************************/
		//String url = "159e8v5701.iok.la";
		String url = "41079543.all123.net";//nat123
		InetAddress ip = InetAddress.getByName(url);
		System.out.println("ip=" + ip); 
		SocketAddress target = new InetSocketAddress(ip, 8002);
		DatagramSocket client = new DatagramSocket();
		String message = "clientA";
		byte[] sendBuf = message.getBytes();
		DatagramPacket pack = new DatagramPacket(sendBuf, sendBuf.length, target);
		client.send(pack);
		
	//	receive(client);
		/********************�ͻ��˽��շ����������Ŀͻ���B��IP��Ϣ**********************************/
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		client.receive(packet);
		String clientB_IP = new String(packet.getData(), 0, packet.getLength());
		System.out.println(clientB_IP);
		
		String[] IP_port = clientB_IP.split(":");
		String clientB_address = IP_port[0];
		int clientB_port = Integer.parseInt(IP_port[1]);
		String requestClientB = "clientB��ã�����clientA";
	
		/*********************�ͻ���A��·�����򶴲�����ͻ���B*************************/
		sendBuf = requestClientB.getBytes();
		
		SocketAddress clientB_Address = new InetSocketAddress(clientB_address, clientB_port);
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, clientB_Address);
		client.send(sendPacket);
		System.out.println("UDPClientA ���͸�UDPClientB ��Ϣ�ɹ�");
		/*********************���տͻ���B��������Ϣ*****************/
		client.receive(packet);
		String clientB_Message = new String(packet.getData(), 0, packet.getLength());
		System.out.println(clientB_Message);
	}
		
}
	
		
	

