package soaktester;

public class TestThread implements Runnable  {
	
	private ITest _test;
	public TestThread(ITest t)
	{
		_test = t;
	}
	@Override
	public void run() {
		
		_test.Execute();
		
	}
	
}
