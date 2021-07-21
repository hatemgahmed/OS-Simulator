# OS-Simulator

A simple Operating System simulator using java threads, volatile booleans for Mutexes, and 3 tasks sharing resources

# Characterestics

–	Processes are implemented using threads and we switch between processes using resume() and suspend().

–	Once the process is created it will have an address in the memory and an ID, parent id will be assigned to it(if applicable). (PCB)

–	The thread runs for a certain amount of time . (timeout)

–	Memory assignment type : next fit.

–	Resource allocation is done using fifo. (queue)

–	The conflict of shared resources is handled using semaphores. (boolean)

–	Resource utilization : utilization is showed as a histogram in the graphical user interface.

–	Interrupt handler : nested.

–	Process requests what it need from the operating system 

–	Scheduling : long term scheduler and short term scheduler are implemented using queues.

–	Scheduling algorithm is RR

–	Memory:  data structure (Array)

–	I/O output is shown by printing to console
