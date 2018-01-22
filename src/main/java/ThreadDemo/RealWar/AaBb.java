package ThreadDemo.RealWar;

import java.util.Random;

public class AaBb {
	public static void main(String[] args) {
		Strbuffer stringBuf=new Strbuffer();
		ThtreadCreate create=new ThtreadCreate(stringBuf);
		ThtreadCustome delete=new ThtreadCustome(stringBuf);
		create.start();
		delete.start();
	}


}
class ThtreadCreate extends Thread{
	Strbuffer strbuffer;
	public ThtreadCreate(Strbuffer strbuffer){
		this.strbuffer=strbuffer;
	}
	public void  run(){
		while (true){
			strbuffer.create();
		}
	}
}
class ThtreadCustome extends Thread{
	Strbuffer strbuffer;
	public ThtreadCustome(Strbuffer strbuffer){
		this.strbuffer=strbuffer;
	}


	public void  run(){
		while (true){
			strbuffer.delete();
		}
	}
}
class Strbuffer{
	StringBuffer stringB=new StringBuffer();
	Random random=new Random();
	public synchronized void create(){
			char a=(char)(random.nextInt(26)+65);
			stringB.append(a);
			System.out.println(Thread.currentThread().getName()+"-----生产了"+a);
			this.notify();
	}

	public synchronized void delete() {

	}
}
