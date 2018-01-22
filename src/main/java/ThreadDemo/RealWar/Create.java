package RealFile;

class StringBufferRes{
	private StringBuffer s=new StringBuffer();
	public synchronized void append() {
		String name=Thread.currentThread().getName();
		char a=(char)(65+(int)(Math.random()*26));
		s.append(a);
		System.out.println("生产了:"+a+"由"+name);
		this.notify();
	}
	public synchronized void delete() throws InterruptedException {
		String name=Thread.currentThread().getName();
		if (s.length()==0) {
			this.wait();
			
		}else {
			char a=s.charAt(0);
			s.deleteCharAt(0);
			System.out.println("读取了:"+a+"由"+name);
		}
		
	}
	
}
class ProT extends Thread {
	StringBufferRes a;
	public ProT(StringBufferRes a) {
		this.a=a;
		
	}
	public void run() {
		while(true) {
			a.append();
		}
	}
	
}

class ComT extends Thread {
	StringBufferRes a;
	public ComT(StringBufferRes a) {
		this.a=a;
		
	}
	public void run() {
		while(true) {
			try {
				a.delete();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
public class Create {
	public static void main(String[] args) {
		StringBufferRes a =new StringBufferRes();
		ProT p=new ProT(a);
		p.start();
		ComT c=new ComT(a);
		c.start();	
	}
	
}


