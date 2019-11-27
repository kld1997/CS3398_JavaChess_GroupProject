package Saves;

import java.io.*;
import Engine.*;
import Options.*;
import GUI.*;

public class SaveState {

    private static SaveState obj = null;
    public static SaveMenu saveMenu;
    public static File[] saveFiles = new File[3];
    public static File dirPath = new File("saveFiles");
    public static Board boardState = null;

    private SaveState() {
        if(!dirPath.exists())
          dirPath.mkdir();
        for(int i = 0; i < 3; i++) {
          String newSaveFile = "saveFile" + i + ".ser";
          File createFile = new File(dirPath, newSaveFile);
          saveFiles[i] = createFile;
          try {
            createFile.createNewFile();
          } catch(IOException e) {
            e.printStackTrace();
          }
        }
        saveMenu = new SaveMenu();
    }

    public static SaveState createMenu() {
	     if(obj == null) {
	        obj = new SaveState();
	     }
       return obj;
    }

    public static SaveState createMenu(Board curBoard) {
        boardState = curBoard;
        return obj;
    }

    public void displaySaveMenu(){
        saveMenu.showSaveMenu();
    }

    public void displayLoadMenu(){
        saveMenu.showLoadMenu();
    }

    public static void initButtons() throws IOException, ClassNotFoundException {
      for(int i = 0; i < 3; i++) {
        try {
          FileInputStream fis = new FileInputStream(saveFiles[i]);
          ObjectInputStream ois = new ObjectInputStream(fis);
          Object obj = ois.readObject();
          if(obj == null) {
            ois.close();
            continue;
          } else {
            saveMenu.saveArray[i].setTitle((String)obj);
            ois.close();
          }
        } catch(Exception e) {
          System.out.println("Empty File Initialized Succesfully");
        }
      }
    }

    public static void serialize(int file) throws IOException {
        String newSaveFile = "saveFile" + file + ".ser";
        File overwrite = new File(dirPath, newSaveFile);
        saveFiles[file] = overwrite;

        String curMode = boardState.options.getMode();
        String[][] temp = ParseBoard.bitboardToArray(boardState.pieceList);
        int curTeam = boardState.teamTurn;
        Object obj = ((Object)temp);
		    FileOutputStream fos = new FileOutputStream(overwrite);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(saveMenu.saveArray[file].curTitle);
        oos.writeInt(curTeam);
		    oos.writeObject(obj);

		    fos.close();
	   }

    public static void deserialize(int load) throws IOException, ClassNotFoundException {
        File fileName = saveFiles[load];
    		FileInputStream fis = new FileInputStream(fileName);
    		ObjectInputStream ois = new ObjectInputStream(fis);
    		Object tObj = ois.readObject();
        String loadTitle = (String)tObj;
        int teamObj = ois.readInt();
        Object bObj = ois.readObject();
        String[][] loadBoard = (String[][])bObj;
        Options op;

        if(loadTitle.contains("WALL"))
          op = new Options("wall");
        else
          op = new Options();

        op.setBoard(loadBoard);
        op.setTurn(teamObj);
    		ois.close();
        boardState = new Board(op);
        new ChessGui(boardState);
    }

}
