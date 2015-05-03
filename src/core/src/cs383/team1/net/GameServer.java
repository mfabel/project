package cs383.team1.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.model.overworld.Position;
import java.io.IOException;


public class GameServer{
        public Server server;
	private boolean running;
        

	public GameServer(final GameManagerInterface gameManager) throws IOException {
		Log.set(Log.LEVEL_DEBUG);

		server = new Server();

		Network.registerKryo(server);

		server.addListener(new Listener() {
			public void connected(Connection c) {
				new ObjectSpace(c).register(Network.GM_ID,
					GameManager.instance);
                        }
		});
		server.addListener(new Listener() {
			public void connected(Connection c) {
				new ObjectSpace(c).register(Network.P_ID,
					GameManager.instance.areas.current.player);
                        }
		});
		server.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof Request) {
					Request r = (Request)object;
					System.out.println(r.s);

					PosResponse pr = new PosResponse();
					pr.pos = GameManager.instance.areas.current.player.pos;
					pr.floatPos = GameManager.instance.areas.current.player.floatPos;
					pr.facing = GameManager.instance.areas.current.player.facing;
					
					connection.sendTCP(pr);
				}
			}
		});
                server.bind(Network.port);
		server.start();

		running = true;
	}

	public void listen(Listener l) {
		server.addListener(l);
	}
}
