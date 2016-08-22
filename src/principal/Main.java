package principal;

public class Main {
	
	public static void main(String[] args) {
		Integer size = 9;
		SudokuMultiThread sudoku = new SudokuMultiThread(size);
		FileHandler file = new FileHandler("9x9.txt", size);
		
		sudoku.model = file.getMatrix();
		
		sudoku.imprimeMatriz();
		
		// start the solution
		double begin = 0;
		try {
			begin = System.currentTimeMillis();
			sudoku.solve(0, 0);
			
		} catch (Exception e) {
			System.out.println("\n"+e.getMessage());
			double end = System.currentTimeMillis();
			System.out.println("Begin: "+begin+"; End: "+end);
			System.out.println("Total: "+(System.currentTimeMillis()-begin));
		}
		
		sudoku.imprimeMatriz();

	}

}
