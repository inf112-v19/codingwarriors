package inf112.project.RoboRally.tcp;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
	private Client client;
	
	void init(Client client) {
		this.client=client;
	}
	
	public void connected(Connection connection) {
		System.out.println("[CLIENT] >> You have connected to " + connection.getRemoteAddressTCP().getHostName());
		MessagePacket message = new MessagePacket();
		message.message="Hello";
		client.sendTCP(message);
		System.out.println("Hello message sent to server");
	}
	
	public void disconnected(Connection connection) {
		System.out.println("[CLIENT] >> You have disconnected");
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof MessagePacket) {
			MessagePacket message = (MessagePacket) object;
			System.out.println("[" +connection.getRemoteAddressTCP().getHostName()+ "] >> " + message.toString());
		}
	}
}
