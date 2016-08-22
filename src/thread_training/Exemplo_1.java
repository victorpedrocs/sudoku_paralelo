package thread_training;

public class Exemplo_1 implements Runnable{
	
	private long begin;
	private int numero;
	
	public Exemplo_1(long begin, int numero) {
		this.begin = begin;
		this.numero = numero;
	}
	
	@Override
	public void run(){
		System.out.println("Tempo de criação da thread " +this.numero+" : " + (System.currentTimeMillis() - this.begin));
	}
	
	public static void main(String[] args) {
		(new Thread(new Exemplo_1(System.currentTimeMillis(), 1))).start();
		(new Thread(new Exemplo_1(System.currentTimeMillis(), 2))).start();
		(new Thread(new Exemplo_1(System.currentTimeMillis(), 3))).start();
		(new Thread(new Exemplo_1(System.currentTimeMillis(), 4))).start();
		(new Thread(new Exemplo_1(System.currentTimeMillis(), 5))).start();
		
	}

}
