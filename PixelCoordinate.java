public class PixelCoordinate
{
	private int xCord,		//Stores X Coord. value.
				    yCord;		//Stores Y Coord. value.
	
	//TEMPORARY PUBLIC
	public int[][] surroundingCords = new int[8][8];
	
	//Overloaded Constructor
	public PixelCoordinate(int x, int y)
	{
		setX(x);
		setY(y);
		setSurroundingCord();
	}
	
	//Set X Coord. value
	public void setX(int x)
	{
		this.xCord = x;
	}
	
	//Set Y Coord. value
	public void setY(int y)
	{
		this.yCord = y;
	}
	
	//Returns X Coord. value
	public int getX()
	{
		return this.xCord;
	}
	
	//Returns Y Coord. value
	public int getY()
	{
		return this.yCord;
	}
	
	//Use Recursion?
	//Return... int array or store as instance variabe?
	
	//THIS IS BROKEN AND NOT THOUGHT OUT WELL.
	public void setSurroundingCord()
	{
		int arrayIterator = 0;
			
		for(int i = this.getX() - 1; i <= this.getX() + 1; i++)
		{
			for (int j = this.getY() -1; j <= this.getY() + 1; j++)
			{
				if(i != this.getX() && j != this.getY())
				{
					this.surroundingCords[arrayIterator][arrayIterator] = i;
					this.surroundingCords[arrayIterator][arrayIterator + 1] = j;
					arrayIterator++;
				}
			}
		}
	}
	
	
}