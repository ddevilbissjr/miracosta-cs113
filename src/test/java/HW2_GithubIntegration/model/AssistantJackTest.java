package HW2_GithubIntegration.model;

/**
 *
 * AssistantJackTest.java: JUnit tester for Assistant Jack
 *
 * @author Nery Chapeton-Lamas
 * @version 1.0
 *
 *  @TODO   test for errors, test rest of assistant jack methods
 */

import org.junit.Assert;
import org.junit.Test;

public class AssistantJackTest {

    @Test
    public void testCheckAnswerSet1() {
        testCheckAnswer(1, 1, 1, 1);
    }

    @Test
    public void testCheckAnswerSet2() {
        testCheckAnswer(2, 6, 10, 6);
    }

    //note there isn’t a "@Test" here! This is a helper for the above methods!!
    public void testCheckAnswer(int set, int w, int l, int m) {
        AssistantJack aj = new AssistantJack(set);
        Assert.assertTrue(aj.checkAnswer(w, l, m) == 0);
    }

}
