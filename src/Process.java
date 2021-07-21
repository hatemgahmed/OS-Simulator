import java.util.ArrayList;
import java.util.Stack;

public class Process extends Thread implements Comparable<Process>{
	
//	protected volatile boolean running=true;
//	protected volatile boolean paused=false;
//	protected final Object pauseLock=new Object();
//	
//	public void Stop() {
//		running=false;
//		resume();
//	}
//	public void pause() {
//		paused=true;
//	}
//	public void resume() {
//		synchronized(pauseLock) {
//			paused=false;
//			pauseLock.notifyAll();
//		}
//	}
//	
	
	protected static enum State{
		New, Ready, Running, Blocked, Finished
	}
	
	int id;
	int pid;
	int userID;
	Process parent;
	ArrayList<Process> children;
	State state;
	Stack<String> kernel;
	Stack<String> user;
	int pc;
	Runnable code;
	String[] programInstructions;
	int nextInstructionToBeExecuted;
	String[] Memory;
	int start;
	int end;
	Integer priority;
	OperatingSystem os;
	boolean resource1needed = false;
	boolean resource2needed = false;
	boolean resource3needed = false;
	
	boolean resource1received = false;
	boolean resource2received = false;
	boolean resource3received = false;
	
	
	Process (int id,int pid, int userID,String program,OperatingSystem os,Runnable r){
		super(r);
		this.id = id;
		this.pid = pid;
		this.userID = userID;
		this.os=os;
		nextInstructionToBeExecuted=0;
		System.out.println("New process is initiated");
//		programInstructions=program.split(":"+"/n");
//		if(programInstructions[0] == "1")
//			resource1needed = true;
//		if(programInstructions[1] == "1")
//			resource2needed = true;
//		if(programInstructions[2] == "1")
//			resource3needed = true;
//		getRssNeeded();
	}
	
	void newToReady(){
		
	}

	void setPriorityy(int priority) {
		this.priority=new Integer(priority);
	}
	
	void getRssNeeded() {
		if(resource1needed) {
			os.setRss1(id);
		}
//		if(resource2needed) {
//			os.setRss2(this);
//		}
//		if(resource3needed) {
//			os.setRss3(this);
//		}
	}
	
	void recieveRss1() {
		os.setRss1(id);
	}
	void returnRss1() {
		os.returnRss1(id);
	}
	
//	void recieveRss1() {
//		resource1received=true;
//	}
//	
//	void recieveRss2() {
//		resource2received=true;
//	}
//	
//	void recieveRss3() {
//		resource3received=true;
//	}
//	
	int getNumberOfInstructions() {
//		return programInstructions.length;
		return 18;
	}
	
	void assignMemory(int start, int end) {
		this.start=start;
		this.end=end;
//		for(int i=start;i<programInstructions.length+start;i++)
		for(int i=start;i<18+start;i++)
//			Memory[start]=programInstructions[i-start];
			Memory[start]="hi";
	}
	
	void giveAccesToMemory(String[] Memory) {
		this.Memory=Memory;
	}
	
	void loadProgramToMemory() {
		
	}
	
	void Running(){
		
		//preemption
		//timeout
		
	}
	void Blocked(){
		
	}
	void Finished(){
		
	}
	@Override
	public int compareTo(Process arg0) {
		return priority.compareTo(arg0.priority);
	}
	
	
}
  