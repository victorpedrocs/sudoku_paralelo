package thread_training;

public class SimpleThread{
	
	static void threadMessage(String message) {
		String threadMessage = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadMessage, message);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		long patience = 1000 * 60 * 60;
		
		if (args.length > 0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
		
		threadMessage("Starting MessageLoop thread");
        long start = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        
        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - start) > patience)
                  && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }
        threadMessage("Finally!");


	}
	
	private static class MessageLoop implements Runnable {
		public void run(){
			String importantInfo[] ={
					"Mensagem 1",
					"Mensagem 2",
					"Mensagem 3",
					"Mensagem 4"
			};
			
			try {
				for(String message : importantInfo){
					Thread.sleep(1000);
					threadMessage(message);
				}
			} catch (InterruptedException e) {
				System.err.println("Não fui executado!");
			}
		}
	}
}
