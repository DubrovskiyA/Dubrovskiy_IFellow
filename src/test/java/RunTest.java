import org.junit.jupiter.api.*;

public class RunTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("BeforeAll!");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("AfterAll");
    }

    @Test
    @DisplayName("Первый тест")
    @Tag("Positive")
    public void test1(){
        System.out.println("Test_1");
        int x=9;
        int y=2;
        int z=x%y;
        Assertions.assertTrue(y>z);
    }
    @Test
    @DisplayName("Второй тест")
    @Tag("Negative")
    public void test2(){
        System.out.println("Test_2");
        String originName="Alex";
        String nameFromAnotherPlace="Bill";
        Assertions.assertEquals(originName,nameFromAnotherPlace,"Имена не совпадают!");
    }
}