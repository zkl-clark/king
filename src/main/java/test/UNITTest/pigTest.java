package test.UNITTest;

import UNITTest.pig;
import org.junit.*;
import org.testng.annotations.AfterClass;


/**
 * pig Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 30, 2017</pre>
 */
public class pigTest {


    pig pig = new pig();
//    @BeforeClass 	针对所有测试，只执行一次，且必须为static void
//    @AfterClass 	针对所有测试，只执行一次，且必须为static void
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("in BeforeClass================");
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("in AfterClass=================");
    }

//    @Before 	初始化方法
//    @After 	释放资源
//    特点是有一个测试方法对应的Before和After就执行一次

    @Before
    public void before() {
        System.out.println("in Before");
    }

    @After
    public void after() {
        System.out.println("in After");
    }

    /**
     * Method: eat()
     */
    @Test
    public void testEat() throws Exception {
        Assert.assertNull(pig.eat());
        //fail("Not yet implemented");
    }

    /**
     * Method: run()
     */
    @Ignore//忽略该测试
    @Test
    public void testRun() throws Exception {

        pig.run();
    }



} 
