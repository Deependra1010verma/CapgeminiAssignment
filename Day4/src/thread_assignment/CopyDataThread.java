package thread_assignment;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyDataThread {
	FileReader reader;
	FileWriter writer;
	
	public CopyDataThread(FileReader reader, FileWriter writer) {
		super();
		this.reader = reader;
		this.writer = writer;
	}

	public void copyData() throws IOException, InterruptedException {
		
			int data;
			int cnt=0;
			
			while((data=reader.read())!=-1) {
				writer.write(data);
				cnt++;
				if(cnt==10) {
					System.out.println("10 Character are copied");
					cnt =0;
					Thread.sleep(5000);
				}
			}
			System.out.println("File Copied Succesfully");
			reader.close();
			writer.close();
			
		} 
	}


