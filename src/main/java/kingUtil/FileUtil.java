package kingUtil;


import java.io.*;
import java.util.*;

public class FileUtil {
    public static void main(String[] args) {
        /*测试getFilesize()*/
        double size=getFilesize("C:\\Users\\Administrator\\Desktop\\Java7bfbcsjsc%28jb51.net%29.rar","MB");
        System.out.println(size+"MB");
         /*测试isFile()*/
        boolean flag=isFile("C:\\Users\\Administrator\\Desktop\\Java7bfbcsjsc%28jb51.net%29.rar");
        System.out.println(flag);
         /*测试isDirectory()*/
        boolean flag2=isDirectory("C:\\Users\\Administrator\\Desktop\\Java7bfbcsjsc%28jb51.net%29.rar");
        System.out.println(flag2);
        /* writeFile*/
          List<String>lists=new ArrayList<String>();
          lists.add("liuwenqiang");
          lists.add("liuwenqiang");
          lists.add("liuwenqiang");
        try{
            writeFile("C:\\Users\\Administrator\\Desktop","新建文本文档.txt",lists,false);
        }
        catch (Exception e){}
        /* */
        lists.clear();
        lists=readFile("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        /*lists.forEach(s -> System.out.println(s));*/

        lists.clear();
        try {
            lists=readFile(new File("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*lists.forEach(s -> System.out.println("------"+s));*/

        Set<String> set=new HashSet<>();
        try {
            set=readFileNoDup("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*set.forEach(s -> System.out.println("*******"+s));*/

        lists.clear();

        try {
            lists=excludeDuplicates("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt","C:\\Users\\Administrator\\Desktop\\新建文本文档 2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*lists.forEach(s -> System.out.println("========"+s));*/

    }



    /**
     * 获取文件大小，单位根据传入的参数决定
     * @param path 文件的完整绝对路径
     * @param units 单位：KB,MB,GB
     * @return 文件的尺寸
     */
    public static double getFilesize(String path, String units){
       if (isFile(path)){
           File file=new File(path);
           double len=file.length();
           double leng=0;
           if("KB".equalsIgnoreCase(units)) {
               leng = len / 1024;
               return leng;
           }
           else if("MB".equalsIgnoreCase(units)) {
               leng = len / 1024 / 1024;
               return leng;
           }
           leng = len / 1024 / 1024 / 1024;
           return leng;
       }
        else {
           return 0;
       }
    }
   /**
    * 有有待改进的地方，普通字符串的包装成路径
    * */
    public static boolean isFile(String path){
        if (path==null){
            return false;
        }else if(new File(path).isDirectory()){
            return false;
        }else if (!new File(path).exists()){
            return false;
        }else {
            return true;
        }
    }
    /**
     * @param path 文件路径
     * @return true 则是文件夹否则不是文件夹
     *
     * */
    public static boolean isDirectory(String path){
        if (path==null){
            return false;
        }else if(new File(path).isFile()){
            return false;
        }else if (!new File(path).exists()){
            return false;
        }else {
            return true;
        }
    }
    /**
     * 删除文件夹下所有文件和子文件夹，但文件夹本身不会被删除
     * @param path 文件夹的完整绝对路径
     * @return 完全清空返回true，否则返回false
     * */
    public static boolean delAllFile(String path){

        if (isDirectory(path)){

            File file=new File(path);
            if (file.isFile()) {
                file.delete();
            }
            else if(file.list().length==0){
                file.delete();
            }
            else {
                File[] files = file.listFiles();
                for (File fi : files) {
                    delAllFile(fi.getAbsolutePath());
                    fi.delete();
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 获取某个文件夹下的文件数量，仅包含该文件夹，不包含子文件夹
     * @param path 文件的完整绝对路径
     * @return 文件夹中的文件数量
     */
    public static int getFileNumber(String path){
        if(isDirectory(path)){
            File file=new File(path);
            int count=0;
            File[] files=file.listFiles();
            for(File fi:files){
                if (fi.isDirectory())
                    count++;
            }
            return count;
        }else {
            return 0;
        }

    }
   /***
    * 删除一个文件
    * @param path 文件的完整绝对路径
    * @return 删除成功返回true，否则返回false
    * */
    public static boolean delFile(String path){
        if(isFile(path)){
            File file=new File(path);
            file.delete();
        }
        return true;
    }
    /**
     * 把文件路径和文件名组合为完整的文件路径
     * @param path 文件的完整绝对路径,不含文件名
     * @param fileName 文件名，含后缀
     * @return 组合后的完整路径
     */
    public static String getFullPath(String path, String fileName){

        if (path.endsWith("\\")||path.endsWith("/"))
            return path+fileName;
        else {
            return path+ File.separator+fileName;
        }

    }
    /**
     * 检查文件夹是否都不为空
     * @param path 文件夹路径,检查该路径下的所有文件夹
     * @return 如果都不为空，则返回true，只要有一个为空就返回false
     */
  public static boolean isNotNullForPaths(String path){
      File file=new File(path);
      if (!isDirectory(path)) {
          return false;
      } else{
          List<File>fileList= Arrays.asList(file.listFiles());
          //这里永远都不为null,
          if (fileList.size()==0){
              return false;
          }else {
              for (File s: fileList){
                  if (s.isDirectory()){
                      if (s.list().length==0) {
                          return false;
                      }
                  }
              }
          }
          return true;
      }
  }
    /**
     * 保存文件到指定路径
     * @param data 文件的内容
     * @param path 文件的完整绝对路径
     * @return 保存成功返回true，否则返回false
     * @throws IOException
     */
    public static boolean writeByteToFile(String path,byte[] data) throws IOException{
        //在这里不能直接调用isFile()函数，因为如果不存在，要创建，而不是直接返回false

        if (writeFile(path, new String(data), true)) return true;
        else return false;
    }

    /**
     * 添加内容到指定文件 如果该文件不存在则创建（内容是指的是一个字符串）
     * @param path 文件的完整绝对路径
     * @param fileContent 要保存的内容
     * @param flag 如果为true，则向现有文件中添加，否则清空并新写入
     * @return 保存完成返回true，否则返回false
     * @throws IOException
     */
   public static boolean writeFile(String path, String fileContent, boolean flag) throws IOException{
       if (mustCreateFile(path)){
           FileWriter fw=new FileWriter(path,flag);
           fw.write(fileContent);
           fw.flush ();
           fw.close ();
           return true;
       }

        return false;
    }
    public static boolean writeFile(String path, List<String> fileContent, boolean flag){
        if(mustCreateFile(path)){
            FileWriter fw=null;
            try {
                fw = new FileWriter(path,flag);
            } catch (IOException e) {
                e.printStackTrace ();
            }
            for (String s:fileContent){
                if (s!=null&&!"".equals(s)) {
                    try {
                        fw.write(s+"\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                fw.close();
            } catch (IOException e) {
                System.err.println ("流关闭失败");
                e.printStackTrace ();
            }
            return true;
        }
        return false;

    }

    /**
     * 添加内容到指定文件 如果该文件不存在则创建（内容是一个list集合）
     * @param path 文件的绝对路径（不含文件名）
     * @param fileName 文件名
     * @param fileContent 要保存的内容集合
     * @param flag 如果为true，则向现有文件中添加，否则清空并新写入
     * @return 保存完成返回true，否则返回false
     * @throws IOException IO异常
     */
    public static boolean writeFile(String path,String fileName, List<String> fileContent, boolean flag) throws IOException{
        String fullpath=getFullPath(path,fileName);//调用了路径构造函数
        if(mustCreateFile(fullpath)){
            FileWriter fw;
            fw = new FileWriter(fullpath,flag);
            fileContent.forEach(s -> {
                if (s!=null&&!"".equals(s)) {
                    try {
                        fw.write(s+"\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            fw.close();
            return true;
        }
        return false;

    }
    /**
     * 文件创建函数，给过参数之后，不论文件或者文件夹是否存在，该参数所指向文件一定存在
     * @param path 指向文件的字符串
     * @return true创建成功，否则创建失败
     * */
    public static boolean mustCreateFile(String path){
        /*文件存在时*/
        if(isFile(path)){
            return true;
        };
        /* 文件不存在 */
        File file1=new File(path);
        try {
            //路径存在，文件本身不存在
            if (file1.getParentFile ().exists ()){
                file1.createNewFile ();
                return true;
            }
            //文件路径不存在
            else {
                file1.getParentFile().mkdirs();
                file1.createNewFile();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 读取一个文件
     * 如果路径错误、文件不存在、为空返回尺寸为0的list
     * @param path 文件的完整绝对路径
     * @return 读取到的文件内容
     * @throws IOException
     */
   public static List<String> readFile(String path) {
       List<String> list=new ArrayList<>();
       if(!isFile(path)) {
           return list;
       }
       else {
           InputStream ins= null;
           try {
               ins = new FileInputStream (path);
           } catch (FileNotFoundException e) {
               e.printStackTrace ();
           }
           InputStreamReader inputStreamReader= null;
           try {
               inputStreamReader = new InputStreamReader (ins, EncodingUtil.getJavaEncode (path));
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace ();
           }
           LineNumberReader LR = new LineNumberReader(inputStreamReader);
           String line = "";
           try {
               while ((line = LR.readLine()) != null) {
                   list.add(line);
               }
           } catch (IOException e) {
               e.printStackTrace ();
           }
           try {
               LR.close ();
           } catch (IOException e) {
               e.printStackTrace ();
           }
           try {
               inputStreamReader.close ();
           } catch (IOException e) {
               e.printStackTrace ();
           }
           try {
               ins.close ();
           } catch (IOException e) {
               e.printStackTrace ();
           }
           return list;
       }

   }
    /**
     * 读取一个文件
     * 如果路径错误、文件不存在、为空返回尺寸为0的list
     * @param file 要读取的文件
     * @return 读取到的文件内容
     * @throws IOException
     */
    public static List<String> readFile(File file) throws IOException{
        return readFile(file.getAbsolutePath());
    }
    /**
     * 读取一个文件,并排重后返回
     * 如果路径错误、文件不存在、为空返回尺寸为0的set
     * @param path 文件的完整绝对路径
     * @return 读取到的文件内容
     * @throws IOException
     */
    public static Set<String> readFileNoDup(String path) throws IOException{
        Set<String> set=new HashSet<>();
        if(!isFile(path)) return set;
        else {
            FileReader fr = new FileReader(path);
            LineNumberReader LR = new LineNumberReader(fr);
            String line;
            while ((line = LR.readLine()) != null) {
                set.add(line);
            }
            return set;
        }

    }
    /**
     * 读取第一个文件，排重后写入第二个文件 并把排重结果返回
     * 如果两个路径中有一个错误、文件不存在、为空返回尺寸为0的list)
     * @param path1 第一个文件的完整绝对路径
     * @param path2 第二个文件的完整绝对路径
     * @return 读取到的文件内容
     * @throws IOException
     */
    public static List<String> excludeDuplicates(String path1, String path2) throws IOException{
        List<String> list=new ArrayList<>();
        Set<String> set=new HashSet<>();
        if(!isFile(path1)||!isFile(path2)) return list;
        else{
            LineNumberReader lR=new LineNumberReader(new FileReader(path1));
            FileWriter fR=new FileWriter(path2,true);
            String line;
            while((line=lR.readLine())!=null){
                if(set.add(line)) fR.write(line+"\r\n");
            }
            fR.close();
            list.addAll(set);//将set集合转化为list
            set=null;
            return list;
        }
    }
}
