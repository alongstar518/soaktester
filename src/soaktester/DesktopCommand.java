package soaktester;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class DesktopCommand extends Syscommand{
	
	private void MouseClick(int x, int y) throws AWTException
	{
		Logger.log(String.format("Clicking %s,%s", x,y));
		Robot robot = new Robot();
		
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		robot.mouseMove(x, y);
		robot.mousePress(mask);
		robot.mouseRelease(mask);
	}
	
	public void MouseClick(String coordinates) throws Exception
	{
		if(coordinates.isEmpty())
			throw new Exception("ClickCoordinates is Empty");
		
		String[]_coordinates = coordinates.split("/");
		
		for(int i = 0 ; i < _coordinates.length;i++)
		{
			String[] c = _coordinates[i].split(",");
			
			MouseClick(Integer.parseInt(c[0]),Integer.parseInt(c[1]));
			
			Thread.sleep(Integer.parseInt(c[2]));
		}
	}
}
