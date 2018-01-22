package Spider;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingcall 2017年-08月-07日,14时-18分
 * Descibe xpath爬虫
 */
public class Spyder1 {
    public static void main(String[] args) {
        String baseurl="http://www.ivsky.com/tupian/ziranfengguang";
       creatUrl ( baseurl );
    }
    public static Document getd(String url){
        Document doc=null;
        Connection conn= Jsoup.connect ( url );
        conn.header ( "User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0" );
        try {
            doc=conn.get ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return doc;
    }
    public static void parse(Document doc){
        Elements e=doc.select ( "div.il_img" );
        List<String> url=new ArrayList<String> (  100);
       /* e.forEach ( s->{
            String albumurl="http://www.ivsky.com"+ s.select ( "a" ).attr ( "href" );
            parsealbum ( albumurl );
        } );*/
    }
    public static void creatUrl(String baseurl){
        for (int i=1;i<=48;i++){
            String url=baseurl+"/index_"+i+".html";
            parse ( getd ( url ) );
        }
    }
    public static void parsealbum(String albumurl){
        Document document=getd(albumurl);
        Elements divs=document.select ( "div.il_img" );
        int i=1;
        for (Element s:divs){
            Element a=s.select ( "a" ).first ();
            String picpage="http://www.ivsky.com/"+a.attr ( "href" );
            parsepage ( picpage,i);
            i++;
        }
    }

    /**
     *
     * @param pageurl
     */
    public static void parsepage(String pageurl,int i){
        Document document=getd(pageurl);
        Element pic=document.select ( "div#pic_con" ).first ().select ( "img" ).first ();
        String picstrurl=pic.attr ( "src" );
        String picname=pic.attr ( "alt");
        URL picurl= null;
        InputStream in=null;
        try {
            picurl = new URL ( picstrurl );
            in =picurl.openStream ();
            FileOutputStream fo=new FileOutputStream ( new File ( "D:\\tmp\\"+picname+"_"+i+".jpg" ));
            int length = 0;
            byte[] buf = new byte[1024*8];
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close ();
            fo.close ();
        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
