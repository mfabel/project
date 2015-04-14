package cs383.team1.client.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.Arrays;

public class Npc implements Entity {
	public static final int TYPE = 3;

	private final Position pos;
        private final ArrayList<String> talk = new ArrayList();
        private int nextLine = -1;
        
	public Npc()
        {
                pos = new Position();
	}

        //Basic Constructor for NPC
        //Takes a position and a dialogue text file name to use as a "script"
	public Npc(Position p, String f)
        {
                String [] lines;
                String fileContents;
                
                f = f.substring(f.indexOf("quests:")+7, f.indexOf("}"));
                
		FileHandle fin = Gdx.files.internal(f);
                if (!fin.exists()) {
                        Gdx.app.error("Npc:", "Invalid Dialogue Filename");
                        talk.add("IAMERROR");
                } else {
                        fileContents = fin.readString();
                        lines = fileContents.trim().split("\n");
                        for (String line : lines) talk.add(line.trim());
                }
                
                Gdx.app.debug("StairsEntity:StairsEntity", "instantiating class");
		pos = p;
	}

        @Override
	public int type()
        {
		return TYPE;
	}

        @Override
	public Position pos()
        {
		return pos;
	}
        
        //Reads the entire script into System.out
        public ArrayList readScript() 
        {
                for (String talk1 : talk) {
                        System.out.println(talk1);
                }
                return talk;
        }
        
        //Reads the line specified by 'i'
        public String readLine(int i)
        {
                if (talk.size() > i)
                        return talk.get(i);
                return talk.get(talk.size()-1);
        }
        
        //Reads the line after the last one just read
        public String readNext()
        {
                nextLine++;
                return readLine(nextLine);
        }
}