package me.Penguin.SuperChatReactions.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

	private File file;

	public CSVHandler(File file) {
		this.file = file;
	}

	public void setupWithHeadings(String... Headings) {
		if (!file.isFile()) {
			try {
				FileWriter writer = new FileWriter(file, true);
				writer.append(String.join(",", Headings));
				writer.append("\n");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
	}

	protected void addline(String... strings) {
		try {
			FileWriter write = new FileWriter(file, true);
			write.append(String.join(",", strings));
			write.append("\n");
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() { return file; }
	
	protected List<String> getLines() {
		List<String> lines = new ArrayList<>();
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			r.readLine();
			String line = "";
			while ((line = r.readLine())!= null) {
				lines.add(line);
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lines;	
	}
	
	public List<String[]> getEntries(){
		List<String> rows = getLines();
		List<String[]> entries = new ArrayList<>();
		for (String s : rows) {
			entries.add(s.split(","));
		}
		return entries;
	}
	
	

}
