package RealFile;

public class Deadclock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object m=new Object();
		Object n=new Object();
		A a=new A(m, n);
		B b=new B(m, n);
		a.start();
		b.start();

	}
}
class A extends Thread{
	Object m,n;
	public A(Object m,Object n) {
		this.m=m;
		this.n=n;
	}
	public void run() {
		synchronized(m) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
			synchronized(n) {
				System.out.println("我执行了——1");
			}
		}
	}
}
class B extends Thread{
	Object m,n;
	public B(Object m,Object n) {
		this.m=m;
		this.n=n;
	}
	public void run() {
		synchronized(n) {
			Thread.yield();
			synchronized(m) {
				System.out.println("我执行了——2");
			}
		}
	}
}
