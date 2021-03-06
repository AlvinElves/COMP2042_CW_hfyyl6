package test;

import java.awt.*;

/**
 * Level class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class Level {

    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int SLOW = 4;
    private static final int SPECIAL = 5;

    private Brick[][] levels;
    private int level;

    private BrickFactory brickFactory;

    private Wall wall;

    /**
     * Constructor for Level that shows the bricks that is on the top of each level
     * @param drawArea size of the bricks level
     * @param brickCount number of bricks
     * @param lineCount number of line
     * @param brickDimensionRatio size of bricks
     * @param wall Wall class
     */
    public Level(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Wall wall){
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        this.wall = wall;

    }

    /**
     * All the rows of bricks are the same brick
     * @param drawArea size of the bricks level
     * @param brickCnt number of bricks
     * @param lineCnt number of lines
     * @param brickSizeRatio size of the brick
     * @param type type of the brick
     * @return the three layer of bricks
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * The bricks are alternate one by one
     * @param drawArea size of the bricks level
     * @param brickCnt number of bricks
     * @param lineCnt number of lines
     * @param brickSizeRatio size of the brick
     * @param typeA first type of brick
     * @param typeB second type of brick
     * @return the three layer of alternate placing bricks
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * Each of the level has different type of bricks and pattern
     * @param drawArea size of the bricks level
     * @param brickCount number of brick
     * @param lineCount number of line
     * @param brickDimensionRatio size of the brick
     * @return each level with different pattern and bricks
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SLOW,SPECIAL);
        return tmp;
    }

    /**
     * Make each of the brick, the colour of bricks
     * @param point coordinate X and Y of the bricks
     * @param size size of the bricks
     * @param type type of the bricks
     * @return bricks that is made
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        brickFactory = new BrickFactory();
        Brick out;
        switch(type){
            case CLAY:
                out = brickFactory.getBrickType("CLAY", point, size);
                break;
            case STEEL:
                out = brickFactory.getBrickType("STEEL", point, size);
                break;
            case CEMENT:
                out = brickFactory.getBrickType("CEMENT", point, size);
                break;
            case SLOW:
                out = brickFactory.getBrickType("SLOW", point, size);
                break;
            case SPECIAL:
                out = brickFactory.getBrickType("SPECIAL", point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    /**
     * Go to the next level if there is more, set the brick based on the level
     */
    public void nextLevel(){
        if (hasLevel()) {
            wall.setBricks(levels[level++]);
            wall.setBrickCount(wall.getBricks().length);
        }
    }

    /**
     * check if there is more levels
     * @return false if reach the final level, true if not
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * get method for level, encapsulating
     * @return which level is player currently at
     */
    public int getLevel(){
        return level;
    }
}