
public class AC extends Process{

	AC(int id, int pid, int userID, String program, OperatingSystem os) {
		super(id, pid, userID, program, os, new Runnable() {
			@Override
			public void run() {
				while(true) {

					os.setRss1(id);
					
					
					
					os.returnRss1(id);
				}
			}
		});
	}

}
