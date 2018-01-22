

import kingUtil.CMDUtil;
import kingUtil.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * ���߳��ļ�����
 * һ���ļ�һ���̣߳�����һ���ļ����Ҿʹ���һ���̣߳�����
 * */

public class MoreThreadCopyFile {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		boolean flag=false;
		while(true) {
            System.out.print("请输入待复制的路径");
            String pathfrom=CMDUtil.folderpath(sc);
            System.out.println();
            System.out.print("请输入目标文件夹的路径");
            String pathto=CMDUtil.folderpath(sc);
            System.out.println();
			new copy(pathfrom,pathto);
		}
	}
}

class copy extends Thread{
    String pathfrom;//具体的文件对象
    String path;//目的地的文件夹路径
	public copy(String pathfrom,String pathto) {
		this.pathfrom=pathfrom;
		this.path=pathto;
		this.start();
	}
	@Override
	public void run() {
	    File file=new File(pathfrom);
	    if (file.isDirectory())
	        try {
                FileEach(file);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
        if (file.isFile()){
            try {
                FileCopy(file,new FileOutputStream(FileUtil.getFullPath(path,file.getName())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 这个函数比较单纯，只负责文件的复制,不负责文件夹的遍历
     */
	public  void FileCopy(File file,FileOutputStream Fout){
        try {
            Files.copy(file.toPath(), Fout);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //负责文件夹的递归，传入的参数肯定是文件夹
    public  void FileEach(File file) throws FileNotFoundException {
            File[] filelist=file.listFiles();
            if (filelist.length!=0){
                for(File f:filelist){
                    if (f.isFile()){
                        FileCopy(f,new FileOutputStream(FileUtil.getFullPath(path,f.getName())));
                    }
                    else FileEach(f);
                }
            }
    }
}


