package reflaction;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.event.ListDataEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingcall 2017年-08月-25日,12时-40分
 * Descibe 所有的获取都几乎可以分为全部获取和指定获取
 */
public class re {
    public static  String name="刘文强";
    public static  String address;
    public re(){
        System.out.println("无参构造函数调用");
    }
    public re(String name){
        System.out.println("有参构造函数调用："+name);
    }
    public static void main(String[] args) {
         getAllmethod();
        //getAllmethodConstructor();
        //callNoArgsMethod();
        //getClassObjectfromConstructor();
        //getAllinterfaces();
        //callKnownMethod();
        //getAllAttributes();
//        getAttributesValue();
//        setAttributesValue();
//        getAttributesValue();
        getclassfrom();
    }
    public static  void callKnownMethod(){
        Class<re> s=getclassfrom();
        try {
            //这个方法需要注意的点比较多
            Method methodspecific=s.getMethod("showint",int.class);
            try {
                methodspecific.invoke(s.newInstance(),44);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void getClassObjectfromConstructor(){
       Constructor[]cons= getAllmethodConstructor();
       re res=null;
        re ress=null;
        try {
            res=(re) cons[0].newInstance();
            ress=(re)cons[1].newInstance("有参构造函数");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        res.sss("没有参数的构造方法");
        ress.ssy();


    }
    public static void callNoArgsMethod(){
        getreObjectNoparam().ssy();
    }
    public static Constructor[] getAllmethodConstructor(){
        //如何获取全部自定义函数
        Constructor[] cons=getclassfrom().getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println(cons[i]);
        }
        return cons;
    }
    public static void getAllmethod(){
        //如何获取全部自定义函数,getMethods()和getDeclaredMethods()
       Method[] methods=getclassfrom().getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }
        System.out.println("总共有："+methods.length+"个方法");
    }
    public static Class getclassfrom(){
        //三种获取class对象的方式
        Class<?> s=null;
//        s=new re().getClass();//--------->来自objetc的方法
//        s=re.class;////java中每个类型都有class 属性.
        try {
         s=Class.forName("reflaction.re");//这个方法需要加包名
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("类的名称是:"+s.getName());
        return s;
    }
    public static re getreObjectNoparam(){
        re res=null;
        try {
            res=(re)getclassfrom().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void getAllinterfaces(){
        Class []classes= getclassfrom().getInterfaces();
        for (Class s:classes)
            System.out.println(s.getName());
    }
    public static  Field[] getAllAttributes(){
        Class s= getclassfrom();
        Field[]fs=s.getFields();
        for (Field f:fs){
            System.out.println(f);
        }
        return fs;
    }
    public static void getAttributesValue(){
        Field[]fs=getAllAttributes();
        for (Field f:fs){
            try {
                System.out.println(f.get(getclassfrom().newInstance()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
    public static void setAttributesValue(){
        Field[]fs=getAllAttributes();
        for (Field f:fs) {
            f.setAccessible(true);//使用反射机制可以打破封装性，导致了java对象的属性不安全。
            try {
                f.set(getclassfrom().newInstance(),"中国人");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }
    public static String show(){
        return "我是中国人";
    }
    public static void  ssy(){
        System.out.println("无参数普通方法被调用");
    }
    public static void  sss(String canshu){
        System.out.println("有参数普通方法被调用："+canshu);
    }
    public static void showint(int m){
        System.out.println("我需要的整数是："+m);

    }
}
