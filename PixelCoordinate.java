public PixelCoordinate
{
	private int xCord,
				    yCord,
					argbInt;
	
	private int[][] surroundingCord;
	
	public PixelCoordinate(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public void setX(int x)
	{
		this.xCord = x;
	}
	
	public void setY(int y)
	{
		this.yCord = y;
	}
	
	public int getX()
	{
		return this.xCord;
	}
	
	public int getY()
	{
		return this.yCord;
	}
	
	public void setSurroundingCord()
	{
		int cordCounter = 0;
	
		for(int i = getX() - 1; i <= getX() + 1; i++)
		{
			for int(j = getY() -1; y <= getY() + 1; j++)
			{
				if(i > 0 && j > 0)
				{
					cordCounter++;
				}
			}
		}

		
	}
}