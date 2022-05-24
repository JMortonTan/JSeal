public class Block {
    private Coordinate main;
    private Coordinate[] surroundingCords = new Coordinate[9];

    //Overloaded Constructor
    public Block(int x, int y) {
        setMain(x, y);
        setSurroundingCord();
    }

    //Set Coord. value
    public void setMain(int x, int y) {
        this.main = new Coordinate(x, y);
    }

    //Returns Coord. object.
    public Coordinate getMain() {
        return this.main;
    }

    //Returns array of Coordinates
    public Coordinate[] getCoordinates(){
        return this.surroundingCords;
    }

    //Fill array of coordinates.
    public void setSurroundingCord() {
        int mX = this.getMain().getX();
        int mY = this.getMain().getY();

        this.surroundingCords[0] = new Coordinate(mX - 1, mY - 1);
        this.surroundingCords[1] = new Coordinate(mX, mY - 1);
        this.surroundingCords[2] = new Coordinate(mX + 1, mY - 1);
        this.surroundingCords[3] = new Coordinate(mX - 1, mY);
        this.surroundingCords[4] = this.getMain();
        this.surroundingCords[5] = new Coordinate(mX + 1, mY);
        this.surroundingCords[6] = new Coordinate(mX - 1, mY + 1);
        this.surroundingCords[7] = new Coordinate(mX, mY + 1);
        this.surroundingCords[8] = new Coordinate(mX + 1, mY + 1);
    }


}