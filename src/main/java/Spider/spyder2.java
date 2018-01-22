package Spider;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import kingUtil.spyderUtil;

/**
 * Created by kingcall 2017年-08月-22日,15时-54分
 * Descibe
 */
public class spyder2 {
    public static void main(String[] args) {
        File file=new File ( "D:\\tmp" );
        for (int p=2;p<=10;p++){
            String url="http://www.mmjpg.com/"+"home/"+p;
            System.out.println (url);
            downloadalbum(url,file);
        }
    }
    private static void downloadalbum(String urlalbum,File filefa){
        Document doc=spyderUtil.getdocByJsoup ( urlalbum );


        Elements el=doc.select ( "span.title" );
        Elements al=el.select ( "a" );
        al.forEach (
                s->{
                    String albumURL=s.attr ( "href" );
                    String name=s.text ();
                    Document docalbum=null;
                    Elements numpage=null;
                    String numsp=null;
                    String[] nums=null;
                    try {
                        docalbum=spyderUtil.getdocByJsoup ( albumURL );
                        numpage=docalbum.select("div.page");
                        numsp=numpage.select ( "a" ).text ();
                        nums=numsp.split ( " " );
                        int num=new  Integer (nums[nums.length-2]);
                        for (int i=1;i<=num;i++){
                            String url= albumURL+"/"+i;
                            Document docpi=spyderUtil.getdocByJsoup ( url );
                            String picstrurl=docpi.select ( "img" ).attr ( "src" );
                            System.out.println (picstrurl);
                            URL picurl=new URL ( picstrurl );
                            InputStream in=picurl.openStream ();
                            FileOutputStream fo=new FileOutputStream ( new File ( filefa, (name+i+".jpg")) );
                            int length = 0;
                            byte[] buf = new byte[1024*8];
                            while ((length = in.read(buf, 0, buf.length)) != -1) {
                                fo.write(buf, 0, length);
                            }
                            in.close();
                            fo.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
        );
    }
}
