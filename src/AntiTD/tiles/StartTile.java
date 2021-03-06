package AntiTD.tiles;

import AntiTD.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Tobias Estefors
 * Tile used for the troops to start from
 */
public class StartTile extends Tile {

    public StartTile() {
        this(null);
    }

    public StartTile(Position pos){
        super(pos);
        setBuildable(false);
        setMoveable(true);
        try {
            setImage(ImageIO.read( this.getClass().getResourceAsStream("/sprites/start.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Draws the tile on the board
     * @param g the board graphics
     */
    @Override
    public void landOn(Graphics g) {
        g.drawImage(getImage(),(int)(getPosition().getX()*(getSize().getWidth())),
                (int)(getPosition().getY()*(getSize().getHeight())),
                (int)getSize().getWidth(),
                (int)getSize().getHeight(),null);
    }
}
