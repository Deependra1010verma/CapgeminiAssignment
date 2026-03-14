package thread_assignment;

import java.io.FileReader;

import java.io.FileWriter;

public class FileProgram {

	public static void main(String[] args){
		
		try {
			
			FileReader reader = new FileReader("source.txt");
			FileWriter writer = new FileWriter("target.txt",true);
			
			CopyDataThread c = new CopyDataThread(reader,writer);
			c.copyData();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
