/*
 * The interface for a single Test
 */
package soaktester;

public interface ITest {
	public void PlayProgram();
	
	public void KillApp();
	
	public void StartApp();
	
	public void Execute();
}
