package principal;

public class Sudoku {
	protected Integer model[][];
	protected Integer size;

	protected boolean checkRow(int row, int num) {
		for (int col = 0; col < size; col++)
			if (model[row][col] == num)
				return false;

		return true;
	}

	/** Checks if num is an acceptable value for the given column */
	protected boolean checkCol(int col, int num) {
		for (int row = 0; row < size; row++)
			if (model[row][col] == num)
				return false;

		return true;
	}

	/** Checks if num is an acceptable value for the box around row and col */
	protected boolean checkBox(int row, int col, int num) {
		row = (row / (size/3)) * (size/3);
		col = (col / (size/3)) * (size/3);

		for (int r = 0; r < (size/3); r++)
			for (int c = 0; c < (size/3); c++)
				if (model[row + r][col + c] == num)
					return false;

		return true;
	}
	
	protected boolean isValid(int row, int col, int num) {
		return checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num);
	}

	public void solve(int row, int col) throws Exception {
		
		// Lança exceção e termina o algoritmo
		if (row > (size-1))
			throw new Exception("Solução encontrada");

		// Se a célula não está vazia, pula para a próxima.
		if (model[row][col] != 0)
			next(row, col);
		else {
			// encontra um número válido para a próxima célula.
			for (int num = 1; num <= size; num++) {
				if (isValid(row, col, num)) {
					model[row][col] = num;

					// Chama a solução para o próximo quadrado recursivamente
					next(row, col);
				}
			}

			// Número válido não encontrado, preenche com 0 e desempilha.
			model[row][col] = 0;
		}
	}

	/** Calls solve for the next cell */
	public void next(int row, int col) throws Exception {
		if (col < (size-1))
			solve(row, col + 1);
		else
			solve(row + 1, 0);
	}
	
	public Sudoku(Integer size) {
		this.size = size;
	}

	public static void main(String[] args) {
		Integer size = 9;
		Sudoku sudoku = new Sudoku(size);
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
	
	protected void imprimeMatriz(){
		for (int row = 0; row < this.size; row++){
			for (int col = 0; col < this.size; col++)
				System.out.print(model[row][col] + " ");
			System.out.println();
		}
	}
}
