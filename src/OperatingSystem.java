import java.awt.EventQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class OperatingSystem {
	
	String[] Memory;
	Queue<Process> newQueue;
//	PriorityQueue<Process> readyQueue;
	Queue<Process> readyQueue;
	HashMap<Integer,Process> blockedProcesses;
	PriorityQueue<Process> kernelProcesses;
	HashMap<Integer, Process> allProcesses;
	Process runningProcess;
	boolean userMode=false;
	int nextEmptyWord=0;
	int processIds=1;
	int desiredNumberIncrease=3;
	
	boolean resource1=false;
	Queue<Process> rss1Queue=new LinkedList<Process>();
	Process rss1Process;
	boolean resource2=false;
	Queue<Process> rss2Queue=new LinkedList<Process>();
	Process rss2Process;
	boolean resource3=false;
	Queue<Process> rss3Queue=new LinkedList<Process>();
	Process rss3Process;
	Queue<Process> interruptedQueue;
	boolean interruptflag = false;
	
	
	public static void main(String[] args) {
		
		new OperatingSystem();
	}
	
	private void initComponents() {
		Memory=new String[1024*1024];
		newQueue=new LinkedList<Process>();
//		readyQueue=new PriorityQueue<Process>();
		readyQueue=new LinkedList<Process>();
		blockedProcesses=new HashMap<Integer,Process>();
		kernelProcesses=new PriorityQueue<Process>();
		allProcesses=new HashMap<Integer,Process>();
	}
	
	public OperatingSystem() {
//		System.out.println("Hatem is the best male tut mate evar");
		
		initComponents();
//	 	up and down
		UPandDOWN p1=new UPandDOWN(processIds++, 0, 1, "", this);
		p1.setPriorityy(0);
		allProcesses.put(p1.id, p1);
		LandR p2=new LandR(processIds++, 0, 1, "", this);
		p2.setPriorityy(1);
		allProcesses.put(p2.id, p2);
		Tilting p3=new Tilting(processIds++, 0, 1, "", this);
		p3.setPriorityy(2);
		allProcesses.put(p3.id, p3);
		AC p4=new AC(processIds++, 0, 1, "", this);
		p4.setPriorityy(3);
		allProcesses.put(p4.id, p4);
		newQueue.add(p1);
		newQueue.add(p2);
		newQueue.add(p3);
		newQueue.add(p4);
		while(true) {
			//timer
			fromNewToReady();
			getReadyForRunning();
//			if(Math.random()>0.8)
//			//	interruptHandler();
//			
//			for(int i=0;i<Math.pow(2, 31)-1;i++) {
//				//check for interrupts
//				//check for preemption
//				//termination?
//			}
//			//user or kernel process to execute next
		}
		

	}
	
	//choosing something from ready queue to run
	void dispatch(Process p) {
		
	}
	
	//long term scheduling
	Process allocateToNewProces() {
		return null;
	}
	
	Process createNewProcess(String program,Runnable r) {
		Process p=new Process(processIds++, 0, 1, program,this,r);
		newQueue.add(p);
		p.state=Process.State.New;
		return p;
	}
	
	boolean fromNewToReady(){
		if(!newQueue.isEmpty())
		{
			Process p=newQueue.poll();
			int numberOfInstructions=p.getNumberOfInstructions();
			if(Memory.length-nextEmptyWord >= numberOfInstructions) {
	//			think of security here
				p.giveAccesToMemory(Memory);
				p.assignMemory(nextEmptyWord, nextEmptyWord+numberOfInstructions+desiredNumberIncrease);
				readyQueue.add(p);
				p.state=Process.State.Ready;
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						p.start();
					}
				});
				p.suspend();
				return true;
			}
			newQueue.add(p);
		}
		return false;
			
	}
	//????
	void assignToReadyQueue() {
		for(int i=0;i<newQueue.size()&&!fromNewToReady();i++);
	}
	
	void getReadyForRunning() {
		//if there is a place for a process to run
		if(runningProcess == null)
		{
			Process p = readyQueue.poll();
//			setRss1(p);
//			setRss2(p);
//			setRss3(p);
			runningProcess = p;
			running(p);
		}
	}
	
	void running(Process p)
	{
		//termination
//		if(termination(p))
//		{
//			finishedRunning(p);
//			return;
//		}
		
		//timer
//		double x;
//		while(true)
//		{
//			x = Math.random();
//			if(x > 0.5)
//			{
//				finishedRunning(p);
//				return;
//			}
//		}
		p.state=Process.State.Running;
		p.resume();
		System.out.println(p.id+" is running now");
		for(int i=0;i<Math.pow(2, 28)-1;i++) ;
		
		p.suspend();
	
		readyQueue.add(p);
		p.state=Process.State.Ready;
		runningProcess=null;
	}
	
	void finishedRunning(Process p)
	{
//		returnRss1(p);
//		returnRss2(p);
//		returnRss3(p);
		runningProcess = null;
		getReadyForRunning();
	}
	
	
	void setRss1(int processID) {
		Process p=allProcesses.get(processID);
		if(resource1) {
			blockedProcesses.put(p.id, p);
			p.state=Process.State.Blocked;
			rss1Queue.add(p);
			schedule();
		}
		else {
			resource1=true;
			rss1Process=p;
			p.recieveRss1();
		}
	}
	
	void returnRss1(int processID) {
		Process p=allProcesses.get(processID);
		if(rss1Process!=p)
			return;
		rss1Process=null;
		if(rss1Queue.isEmpty()) 
			resource1=false;
		else {
			Process r1 = rss1Queue.poll();
			setRss1(r1.id);
			if(r1.resource2needed||r1.resource3needed) {
				
			}
			blockedProcesses.remove(r1.id);
			readyQueue.add(r1);
		}
	}

//	void setRss2(Process p) {
//		if(resource2) {
//			blockedProcesses.put(p.id, p);
//			p.state=Process.State.Blocked;
//			rss2Queue.add(p);
//			schedule();
//		}
//		else {
//			resource2=true;
//			rss2Process=p;
//			p.recieveRss2();
//		}
//	}
//	
//	void returnRss2(Process p) {
//		if(rss2Process!=p)
//			return;
//		rss2Process=null;
//		if(rss2Queue.isEmpty()) 
//			resource2=false;
//		else {
//			Process r2 = rss2Queue.poll();
//			setRss2(r2);
//			blockedProcesses.remove(r2.id);
//			readyQueue.add(r2);
//		}
//		
		
//	}
	
//	void setRss3(Process p) {
//		if(resource3) {
//			blockedProcesses.put(p.id, p);
//			p.state=Process.State.Blocked;
//			rss3Queue.add(p);
//			schedule();
//		}
//		else {
//			resource3=true;
//			rss3Process=p;
//			p.recieveRss3();
//		}
//	}
//	
//	void returnRss3(Process p) {
//		if(rss3Process!=p)
//			return;
//		rss3Process=null;
//		if(rss3Queue.isEmpty()) 
//			resource3=false;
//		else {
//			Process r3 = rss3Queue.poll();
//			setRss3(r3);
//			blockedProcesses.remove(r3.id);
//			readyQueue.add(r3);
//		}
//		
//		
//	}
	
	void schedule(){
		
	}
	
	
	void interruptHandler(Process p) {
		if(interruptflag) {
			//then there is already an interrupt running
			interruptedQueue.add(p);
		}
		else {
			// handle interrupt
			if(interruptedQueue.isEmpty()) {
				interruptflag = true;
				blockedProcesses.put(this.processIds, runningProcess);
				runningProcess = p;	
			}
			else
			{
				Process p1 = interruptedQueue.poll();
				runningProcess = p1;
				interruptedQueue.add(p);
			}
			
			//readyQueue.add(p);
		}
	}
		boolean termination(Process p){
			
		double ter = Math.random();
		if(ter > 0.9) {
			return true;
		}
		return false;
	}
}
