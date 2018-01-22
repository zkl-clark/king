package kingUtil;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/28.
 */
public class CMDUtil {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(folderpath(sc));
    }
    public static String folderpath(Scanner sc){
        String rowpath=sc.nextLine();
        String path="";
        if(rowpath.contains("\""))
            rowpath.substring(1,rowpath.length()-1);
        //构造完整文件路径
        path=rowpath.replace("\\","/");
        return path;
    }
}
