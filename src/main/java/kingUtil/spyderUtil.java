package kingUtil;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * Created by kingcall 2017年-08月-23日,21时-02分
 * Descibe
 */
public class spyderUtil {
    public static Document getdocByJsoup(String url){
        Connection connection=null;
        Document doc=null;
        connection= Jsoup.connect ( url );
        connection.header ( "User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0" );
        try {
            doc=connection.get ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return doc;
    }
}
