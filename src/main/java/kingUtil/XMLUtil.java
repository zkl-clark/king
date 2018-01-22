package kingUtil;
import com.google.common.io.Resources;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kingcall 2017年-08月-22日,22时-03分
 * Descibe
 */
public class XMLUtil {
    public static void main(String[] args) {
        readXml();
       // writeXml ( createXml () );

    }
    public static Element readXml(){
        SAXReader reader=new SAXReader (  );
        Document document=null;
        try {
            document=reader.read ( Resources.getResource("Project/Vote/Config/000001.xml") );
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        //获取根元素时必须的，因为文档对象不支持获取其他节点，只支持获取跟节点，
        Element rootelement=document.getRootElement ();
        //获取单个节点
        Element e1=rootelement.element ( "votetitle" );
        //获取节点的文本
        System.out.println (e1.getText ());
        //获取属性对应的文本
        System.out.println (e1.attribute ( "title" ).getText ());
        //element()方法不支持跳级选取元素，elements()获取所有的节点
        List nodes = rootelement.element("options").elements ("option");
        System.out.println ("特定的节点个数"+nodes.size ());
        System.out.println ("全部的节点个数"+rootelement.elements ().size ());
        //遍历全部option节点
        for (Object e:nodes){
            System.out.println (((Element)e).getText ());
        }
        //遍历全部option节点
        Element optionss=rootelement.element ( "options" );
        Iterator<Element>it=optionss.elementIterator ();
        while (it.hasNext ()){
            System.out.println (it.next ().getText ());
        }
        //遍历全部option节点——通过特定的名称
        Iterator<Element>it1=optionss.elementIterator ("option");
        while (it1.hasNext ()){
            System.out.println (it1.next ().getText ());
        }
        System.out.println ("直接获取节点内容————"+  rootelement.elementText ( "votetitle" ));
        //超级遍历整个文档
        System.out.println ("----------------------------------------");
        rootelement.accept ( new VisitorSupport () {
            @Override
            public void visit(Element node) {
                super.visit ( node );
                System.out.println(node.getText ());
            }
            @Override
            public void visit(Attribute node) {
                super.visit ( node );
                System.out.println(node.getText ());
            }
        } );
        //xpath提取方法，弥补单层提取的不足之处
        System.out.println ("----------------------------------------");
        List<Element>elements=document.selectNodes ( "//option" );
        elements.forEach ( s-> System.out.println (s.getText ()+s.valueOf ( "@num" )) );
       return rootelement;
    }
    public static Document createXml(){
        //父级元素往往是有用的，将来方便添加子元素，对于一个单独的符非父级元素建议一次性完成，得到的标签相当于一个句柄，可以方便后续操作
        Document document= DocumentHelper.createDocument ();
        Element root=document.addElement ( "root" );
        Element options=root.addElement ( "options" );
        options.addElement ( "option" ).addText ( "选项一" ).addAttribute ( "num" ,"10");
        Element option=options.addElement ( "option" );
        option.addText ( "选项二" );
        option.addAttribute ( "num","20" );
        Element opt=options.addElement ( "option" );
        opt.setText ( "选项三" );
        opt.addAttribute ( "num","100" );
        return  document;
    }
    public static void writeXml(Document document){
        FileWriter fw=null;
        try {
            fw=new FileWriter ( "C:\\Users\\Administrator\\Desktop\\demo.xml" );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        OutputFormat format=OutputFormat.createPrettyPrint ();
        format.setEncoding ( "UTF-8 ");
        XMLWriter xw=new XMLWriter ( fw,format );
        try {
            xw.write ( document );
            xw.close ();
            fw.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
