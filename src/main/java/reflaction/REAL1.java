package reflaction;

import javax.lang.model.element.Name;

/**
 * Created by kingcall 2017年-08月-27日,19时-32分
 * Descibe 高速你为什么要获取构造方法
 */
class book{
    public book(){
        System.out.println("这是一本书");
    }
    public book(String name){
        System.out.println("这是一本"+name+"书");
    }
}
public class REAL1 {
    public static void main(String[] args) {

        try {
            Class<?> cs=Class.forName("reflaction.book");
            try {
               cs.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
