package CourseExamples;

import org.junit.*;

public class Junit {
            @BeforeClass
        public static void beforeMyClass(){
            System.out.println("beforeMyClass");
        }
        @Before
        public void beforeEachTest(){
            System.out.println("beforeEachTest");
        }
        @Test
        public void myFirstTest(){
            System.out.println("myTest");
        }
        @After
        public void afterEachTest(){
            System.out.println("afterEachTest");
        }
        @AfterClass
        public static void afterClassEnds(){
            System.out.println("afterClassEnds");
        }
    }

//Junit reference - http://junit.org/junit4/