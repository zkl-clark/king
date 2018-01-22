package InterFace.Demo.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by kingcall 2017年-08月-08日,09时-57分
 * Descibe
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }
    String name;
    int age;
    Sex gender;
    String emailAddress;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void printPerson() {
        System.out.println (name+"  ");
    }
    public static void printPersonsWithinAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }
    //定义了一个静态接口,怎么给接口传参数进去-----------------------------------》
    static Predicate<Person> Check=new Predicate<Person> () {
        int low=10;
        int high=20;
        @Override
        public boolean test(Person person) {
           if (low <= person.getAge() && person.getAge() < high)
               return true;
           else
               return false;
        }
    };
    public static void printPersons(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }
}
