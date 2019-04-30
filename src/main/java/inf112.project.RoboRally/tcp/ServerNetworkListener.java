package inf112.project.RoboRally.tcp;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerNetworkListener extends Listener {
	ServerNetworkListener() {
	
	}
	
	public void connected(Connection c) {
		System.out.println(c.getRemoteAddressTCP().getHostName() + " has connected");
		MessagePacket welcome = new MessagePacket();
		welcome.message="Welcome";
		c.sendTCP(welcome);
	}
	
	public void disconnected(Connection c) {
		System.out.println("Someone has disconnected");
	}
	
	public void received(Connection c, Object o) {
		if (o instanceof MessagePacket) {
			MessagePacket messagePacket = (MessagePacket) o;
			System.out.println(c.getRemoteAddressTCP().getHostName() +": " + messagePacket.toString());
		}
	}
}
