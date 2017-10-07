package TestNG_Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FirstClassTest {

    private Path path;
    private File testFile;
    private String FileName = "test.txt";


    @BeforeClass(alwaysRun = true)
    public void createTheDirectory() throws IOException {
        path = Files.createTempDirectory("testFiles");
    }

    @Test(groups = "positive")
    public void test1() throws IOException {
        testFile = new File(path + "/" + FileName);
        if (!testFile.exists()) {
            testFile.createNewFile();
        }
    }

    @Test(groups = "negative")
    public void test2() throws IOException {
        Assert.assertFalse(testFile.createNewFile());
    }

    @Test(groups = "positive")
    public void test3(){
        Assert.assertEquals(testFile.getName(), FileName);
    }

    @Test(groups = "positive")
    public void test4(){
        Assert.assertEquals(testFile.length(), 0);

    }

    @AfterClass(alwaysRun = true)
    public void deleteTheDirectory() throws IOException {
        if (testFile.exists()){
            testFile.delete();
        }
        Files.delete(path);
    }

}
