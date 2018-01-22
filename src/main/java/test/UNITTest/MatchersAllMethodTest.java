package test.UNITTest;

import UNITTest.MatchersAllMethod;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MatchersAllMethod Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 30, 2017</pre>
 */
public class MatchersAllMethodTest {

    MatchersAllMethod mm=new MatchersAllMethod();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getint()
     */
    @Test
    public void testGetint() throws Exception {
        Assert.assertThat(mm.getint(), Matchers.greaterThanOrEqualTo(10));
        Assert.assertThat(mm.getint(), Matchers.greaterThanOrEqualTo(8));
        Assert.assertThat(mm.getint(), Matchers.lessThan(15));
        Assert.assertThat(mm.getint(), Matchers.lessThanOrEqualTo(15));
        Assert.assertThat(mm.getint()+0.1, Matchers.closeTo(10,0.5));



    }

    /**
     * Method: getstring()
     */
    @Test
    public void testGetstring() throws Exception {
//TODO: Test goes here... 
    }


} 
