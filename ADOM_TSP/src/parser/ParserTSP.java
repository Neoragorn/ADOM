package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import datastorage.Data;

public class ParserTSP {

	private File file;
	
	public void openFile(String name)
	{
		file = new File(name);
		if (!file.exists())
			System.out.println("Wrong path name");
	}
	
	public void readAndParseFile() throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i <= 5; i++)
			br.readLine();
		
		String line;
		while((line = br.readLine()) != null){	
			if (!line.equals("EOF"))
		     parsingFile(line);
		}
		br.close();
	}
	
	public void parsingFile(String data)
	{
		String[] tokens = data.split(" ");		
		Data.getInstance().addData(tokens);
	}
}
