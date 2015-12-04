package AntiTD;

import AntiTD.tiles.Tile;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.sound.midi.SysexMessage;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.Semaphore;

/**
 * Created by dv13tes on 2015-11-27.
 */
public class Handler extends Thread {
    private static LinkedList<GameObject> objects;
    private int tid;
    private Thread thread;


    public Handler(int tid){
        this.tid=tid;
        objects=new LinkedList<>();
        thread=new Thread(this);
        thread.start();
    }


    public static synchronized void  clearList(){
        int i=objects.size()-1;
        while(objects.size()!=0){
            objects.remove(i);
            i--;
        }
    }
    public void addObject(GameObject object){
        objects.add(object);
    }
    public static void removeObject(GameObject object){
        objects.remove(object);
    }

    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
                objects.get(i).tick();
        }
    }
    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            try {
                GameObject gameObject = objects.get(i);
                g.setColor(Color.blue);
                int sizeX = (int) gameObject.getTilePosition().getSize().getWidth();
                int sizeY = (int) gameObject.getTilePosition().getSize().getHeight();

                Position position = gameObject.getTilePosition().getPosition();
                double x_start = (position.getX() * sizeX) * 1.0;
                double y_start = (position.getY() * sizeY) * 1.0;

                Tile moveTo = gameObject.getMoveToPosition();
                double x_to = (moveTo.getPosition().getX() * sizeX) * 1.0;
                double y_to = (moveTo.getPosition().getY() * sizeY) * 1.0;

                Double progress = (gameObject.getMoveProgres() * 1.0) / 100.0;
                double x_global = x_start - x_to;
                double y_global = y_start - y_to;

                Long x_current = Math.round(x_start - (x_global * progress.doubleValue()));
                Long y_current = Math.round(y_start - (y_global * progress.doubleValue()));
                int troopSizeX= (int) gameObject.getTilePosition().getSize().getWidth()/3;
                int troopSizeY=(int)gameObject.getTilePosition().getSize().getHeight()/3;
                //int x = Math.round(position.getX()*size+(size*progress));
                //int y = Math.round(position.getY()*size+(size*progress));
                g.fillRect(x_current.intValue(), y_current.intValue(), troopSizeX, troopSizeY);
            }catch (NullPointerException e){
               System.out.println("plz slow down..");
            }
        }
    }

}
