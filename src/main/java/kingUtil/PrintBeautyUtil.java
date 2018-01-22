package kingUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kingcall 2017年-08月-12日,19时-39分
 * Descibe
 */
public class PrintBeautyUtil {
    /**
     * 美化函数
     * @param s
     * @return
     */
    public  static String beautfy(String s){
        boolean flag=false;
        int ban=0;
        int len=s.length ();
        int count=0;
        if (isContainChinese ( s )) {
            count=ChineseLong ( s );
            ban = (20 - count * 2) / 2;
        }
        else{
            ban=(20-len)/2;
            if (s.length ()%2!=0)
                flag=true;
        }
        for (int i=0;i<ban;i++)
            s=" "+s+" ";
        if (flag)
            s=" "+s+" "+" ";
        else
            s=" "+s+" ";
        return s;
    }
    /**
     * 判断字符串是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    public static int ChineseLong(String str){
        int count=0;
        char[] c = str.toCharArray();
        for(int i = 0; i < c.length; i ++)
        {
            String len = Integer.toBinaryString(c[i]);
            if(len.length() > 8)
                count ++;
        }
        return count;
    }
}
