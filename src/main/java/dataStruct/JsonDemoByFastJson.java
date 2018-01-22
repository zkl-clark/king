package dataStruct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingcall 2017年-09月-13日,15时-24分
 * Descibe
 */
public class JsonDemoByFastJson {
    public static void main(String[] args) {
        JsonDemoByFastJson jb=new JsonDemoByFastJson();
        //jb.simpleBean();
       // jb.simpleMap();
        jb.complexMap();

    }
    public  void simpleBean(){
        UserBean user=new UserBean("001","刘文强");
        System.out.println(user.id);
        System.out.println(user.name);

        String json1= JSON.toJSONString(user);
        System.out.println("Json格式"+json1);
        System.out.println("=====================================");
        String json2="{\"002\":\"刘备\"}";
        UserBean u2=JSON.parseObject(json2, UserBean.class);
        System.out.println(u2.id);
        System.out.println(u2.name);
    }
    public void simpleMap(){
        Map<String,String> map1=new HashMap<>();
        map1.put("001","刘彻");
        map1.put("002","刘备");
        map1.put("003","刘邦");
        String json1=JSON.toJSONString(map1);
        System.out.println(json1);
        Map<String,String>map2=JSON.parseObject(json1,Map.class);
        System.out.println(map2.size());
        System.out.println(map2.get("003"));
    }
    public void complexMap(){
        Map<String,UserBean> map=new HashMap<>();
        map.put("001",new UserBean("001","刘邦"));
        map.put("002",new UserBean("002","刘备"));
        String json1=JSON.toJSONString(map);
        System.out.println(json1);
        //下面是固定格式，请务必记住
        Map<String,UserBean>map1=JSON.parseObject(json1,new TypeReference<Map<String,UserBean>>(){});
        System.out.println(map1.size());
        //----------------------------<
        System.out.println(map1.get("001").getId());
        System.out.println(map1.get("001").id);
        System.out.println(map1.get("001").getName());

    }
}
