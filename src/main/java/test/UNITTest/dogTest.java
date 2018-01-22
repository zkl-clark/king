package test.UNITTest;

import UNITTest.dog;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * dog Tester.
 * 通的选择运行即可，发现也可以在方法名上单击，运行单个方法
 *
 * @author <Authors name>
 * @version 1.0
 */
public class dogTest {

    dog dog = new dog();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("in BeforeClass================");
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("in AfterClass=================");
    }


    /**
     * Method: factorial(int n)
     */
    @Test
    public void testFactorial() throws Exception {
        Assert.assertEquals(120, dog.factorial(5));
        Assert.assertThat(dog.factorial(5), Matchers.greaterThan(100));
    }

    /**
     * Method: fibonacci(int n)
     */
    @Test
    public void testFibonacci() throws Exception {
        Assert.assertEquals(3, dog.fibonacci(5));
        Assert.assertThat(120.2, Matchers.closeTo(120, 5));
    }


} 
