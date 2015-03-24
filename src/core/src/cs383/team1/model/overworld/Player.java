package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public final class Player implements Entity {
	public Position pos;
        public String playerDirection;
        
	public int hp;
	public int mp;
	public int ap;

	public Player() {
		this(new Position(0, 10));
                playerDirection = "down";
	}

	public Player(Position p) {
		Gdx.app.debug("Player:Player", "instantiating class");

		pos = p;
                playerDirection = "down";
	}

	public int type() {
		/* TODO: implement later */
		return -1;
	}

	public Position pos() {
		return pos;
	}
}