package p2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class UDPClientB {

	public static void main(String[] args) throws IOException {
		
	    System.out.println("�ͻ���B�Ѿ�����");
		/*************�ͻ���B����������������*************************/
	   // String url = "159e8v5701.iok.la";
	    String url = "41079543.all123.net";//nat123
		InetAddress ip = InetAddress.getByName(url);
		System.out.println("ip=" + ip); 
		SocketAddress target = new InetSocketAddress(ip, 8002);// 42982
		DatagramSocket client = new DatagramSocket();
		String message = "clientB";
		byte[] sendBuf = message.getBytes();
		DatagramPacket pack = new DatagramPacket(sendBuf, sendBuf.length, target);
		client.send(pack);
		
	//	receive(client);
		/********************�ͻ��˽��շ����������Ŀͻ���A��IP��Ϣ**********************************/
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		client.receive(packet);
		String clientA_IP = new String(packet.getData(), 0, packet.getLength());
		System.out.println(clientA_IP);
		
		String[] IP_port = clientA_IP.split(":");
		String clientA_address = IP_port[0];
		int clientA_port = Integer.parseInt(IP_port[1]);
		String requestClientA = "clientA��ã�����clientB";
		
		/*********************�ͻ���B��·�����򶴲�����ͻ���A*************************/
		sendBuf = requestClientA.getBytes();
		
		SocketAddress clientA_Address = new InetSocketAddress(clientA_address, clientA_port);
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, clientA_Address);
		client.send(sendPacket);
		System.out.println("UDPClientB ���͸�UDPClientA ��Ϣ�ɹ�");
		
		/***************************���տͻ���A��������Ϣ*********************************/
		client.receive(packet);
		String clientA_Message = new String(packet.getData(), 0, packet.getLength());
		System.out.println(clientA_Message);
	}
	
}
