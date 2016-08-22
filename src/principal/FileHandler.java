package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
	private BufferedReader bufferedReader;
	
	private Integer[][] board;
	
	
	public FileHandler(String fileName, Integer size) {
		try {
			this.bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			readFileToArray(size);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void readFileToArray(Integer size) throws IOException {
		this.board = new Integer[size][size];
		String line;
		int i = 0;
		int j = 0;
		
		while((line = this.bufferedReader.readLine()) != null){
			for (String number : line.split(" ")) {
				this.board[i][j] = Integer.parseInt(number);
				j++;
			}
			
			j = 0;
			i++;
		}
	
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder("Array:\n");
		
		for (Integer[] integers : board) {
			for (Integer integer : integers) {
				text.append(integer).append(" ");
			}
			text.append("\n");
		}
		
		return text.toString();
	}
	
	public Integer[][] getMatrix() {
		return this.board;
	}
}
