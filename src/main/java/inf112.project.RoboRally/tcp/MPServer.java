package inf112.project.RoboRally.tcp;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MPServer {
	private int serverPortTCP = 8080;
	private int serverPortUDP = 8081;
	private Server server;
	private ServerNetworkListener snl;
	private Kryo kryo;
	
	public static void main(String[] args) throws UnknownHostException {
		new MPServer();
	}
	
	private MPServer() throws UnknownHostException {
		server = new Server();
		System.out.println("Your IP is " + InetAddress.getLocalHost().toString());
		registerPackets();
		snl = new ServerNetworkListener();
		server.addListener(snl);
		try {
			server.bind(serverPortTCP, serverPortUDP);
		} catch (IOException e) {
			System.out.println("Could not bind ports");
			e.printStackTrace();
		}
		server.start();
		try {
			server.update(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MessagePacket messagePacket = new MessagePacket();
		Scanner keyboard = new Scanner(System.in);
		while(true) {
			messagePacket.message=keyboard.nextLine();
			server.sendToAllTCP(messagePacket);
		}
	}
	
	private void registerPackets() {
		kryo = server.getKryo();
		kryo.register(MessagePacket.class);
	}
}
