package AntiTD.troops;

import AntiTD.tiles.Tile;

import java.awt.*;

/**
 * Created by dv13trm on 2015-11-27.
 */
public class TeleportTroop extends Troop {

    private Tile teleportStartTile;
    private Tile teleportEndTile;

    private int tpMoves;
    private boolean isTeleporting;

    private int tpLength = 3;

    static private final int MAX_HEALTH = 10;
    static private final int KILL_DEATH_SCORE = 100;
    static private final double SPEED = 2;

    /**
     * Constructor for teleport troop
     * @param pos Starting tile position.
     */
    public TeleportTroop(Tile pos) {
        this(null, pos);
    }

    /**
     * Constructor for teleport troop
     * @param img Image used for rendering this object.
     * @param pos Starting tile position.
     */
    public TeleportTroop(Image img, Tile pos) {
        this(img, pos, MAX_HEALTH, KILL_DEATH_SCORE, SPEED, null);
    }

    /**
     * Constructor for teleport troop, used for overriding health score speed
     *
     * ** CAUTION **
     * Use this constructor for test purposes only.
     * @param img Image used for rendering this object.
     * @param pos Starting tile position.
     * @param health Damage the troop can sustain.
     * @param score Score generated if this troop reaches goal.
     * @param speed Move progress value for every tick. Should be a value between
     *              0 and 100. The tick will increase the move progress with this
     *              value and when progress reaches 100 the move is finished.
     */
    public TeleportTroop(Image img, Tile pos, int health, int score, double speed, Integer teleportLength) {
        super(img, pos, health, score, speed);
        if (teleportLength != null) {
            tpLength = teleportLength.intValue();
        }
        isTeleporting = false;
        tpMoves = 0;
    }

    @Override
    public void tick() {
        Tile currentPosition = this.getTilePosition();
        if (isTeleporting) {
            if (tpMoves == 0) {
                teleportStartTile = this.getTilePosition();
            } else if (tpMoves >= tpLength) {
                isTeleporting = false;
                tpMoves = 0;
                teleportEndTile = this.getTilePosition();
                teleportStartTile.setTeleportTo(teleportEndTile);
            }
            else if (isAlive()){
                addTeleportException(currentPosition);
            }else{
                /* Clearar listan på tiles mellan teleporten om teleportgubben dör innan den är färdig med teleporten*/
                clearTeleports();
            }
        }
        this.move();
    }


    @Override
    protected void move() {
        if (isTeleporting) {
            Tile currentPosition = this.getTilePosition();
            super.move();
            if (this.getTilePosition() != currentPosition) {
                tpMoves++;
            }
        } else {
            super.move();
        }
    }


    @Override
    public void render(Graphics g) {

    }

    public void initTeleport() {
        isTeleporting = true;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }
}
