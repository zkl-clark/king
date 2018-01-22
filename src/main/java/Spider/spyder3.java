package Spider;

import org.jsoup.nodes.Document;
import kingUtil.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by kingcall 2017年-08月-23日,21时-00分
 * Descibe
 */
public class spyder3 {
    public static void main(String[] args) {
        String baseurl="http://www.mmjpg.com";
        creatUrl(baseurl);
    }
    public static void creatUrl(String baseurl){
        for (int i=1;i<=73;i++){
            String url=baseurl+"/home/"+i;
            Document doc=spyderUtil.getdocByJsoup(url);
            Element e=doc.select("div.pic").first();
            Elements li=e.select("li");
            //支持对集合中的元素统一获取的特性
            Elements a=li.select("img");
        }
    }
}
