package tests.utils;

import java.io.File;

import org.junit.Test;

import utils.Pathes;

public class PathesTest {

    @Test
    public void test1() {
	System.out.println(Pathes.BASE); 
    }
    
    @Test
    public void test2() {
    	File folder = new File(Pathes.TEST_FOLDER_1);
    	File[] listOfFiles = folder.listFiles();
    	if(listOfFiles == null ) return; 
    	
    	for (File file : listOfFiles) {
    	    if (file.isFile()) {
    	        System.out.println(file.getName());
    	    }
    	}
    }    
}
