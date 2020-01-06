package soaktester;

public abstract class DeviceTest implements ITest{
	public String ProcessName = "com.ericsson.mediaroom.tv3client/ericsson.tv.cloud.client.android.MainActivity"; 
	public String Platform; 
	public String TestDuration = "01:00:00";
	public Camera Camera;
	public String Ip;
	public String ClickCoordinates;
	public int TestInteration = 6;
	
	public void StartRecording() {
		
		Camera.StartRecording();
		String[] time = TestDuration.split(":");
		try {
			Thread.sleep(Integer.parseInt(time[0]) * 3600*1000 + Integer.parseInt(time[1])*60*1000 + Integer.parseInt(time[2])*1000);
			Logger.log("Waiting for Camera stop..");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void PlayProgram() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void KillApp() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void StartApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Execute()
	{
			while(true)
			{
				runTest();
				try {
					Thread.sleep(1000*Sysconfig.loopDelaySeconds);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Sysconfig.TestInteration++;
			}
	}
	
	
	private void runTest()
	{
		KillApp();
		StartApp();
		PlayProgram();
		StartRecording();
		KillApp();
	}
}
