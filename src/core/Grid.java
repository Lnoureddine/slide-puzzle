package core;

public class Grid {
	int lignes;
	int colones;
	Case[][] cases;

	public Grid(int l, int c) {
		lignes = l;
		colones = c;
		cases = new Case[l + 2][c + 2];
		for (int i = 0, k = 1; i < lignes + 2; i++) {
			for (int j = 0; j < colones + 2; j++) {
				cases[i][j] = new Case(k);
				cases[i][j].setState(Case.FULL);
				if (i == 0 || j == 0 || i == lignes + 1 || j == colones + 1) {
					cases[i][j].setState(Case.BORDER);
					cases[i][j].setId(-1);
				} else {
					k++;
				}
			}
		}
		cases[lignes][colones].setState(Case.EMPTY);
		start();
	}

	public void start() {
		int n = 1;
		while (n < 1000) {
			randomize();
			n++;
		}

	}

	public Case getCase(int i, int j) {
		return cases[i + 1][j + 1];
	}

	private void randomize() {
		Case[] m = movables();
		int c = 0;
		for (int i = 0; i < 4; i++) {
			if (m[i] != null) {
				c++;
			}
		}
		int r = (int) (Math.random() * c + 1);
		int[] t = getCorFromId(m[r - 1].getId());
		move(t[0], t[1], false);
	}

	public boolean userMove(int i, int j) {
		return move(i, j, true);
	}

	private boolean move(int i, int j, boolean user) {
		int id = isMovable(i - 1, j - 1);
		if (id > 0) {
			int[] t = getCorFromId(id);
			Case tmp;
			tmp = cases[i][j];
			cases[i][j] = cases[t[0]][t[1]];
			cases[t[0]][t[1]] = tmp;
		}
		if (user) {
			return hasWin();
		}
		return false;
	}

	private boolean hasWin() {
		for (int i = 0, k = 1; i < lignes + 2; i++) {
			for (int j = 0; j < colones + 2; j++) {
				if (i == 0 || j == 0 || i == lignes + 1 || j == colones + 1) {
					cases[i][j].setState(Case.BORDER);
				} else {
					if (cases[i][j].getId() != k) {
						return false;
					}
					k++;
				}
			}
		}
		return true;
	}

	private int[] getCorFromId(int id) {
		for (int i = 0; i < lignes + 2; i++) {
			for (int j = 0; j < colones + 2; j++) {
				if (cases[i][j].getId() == id) {
					int[] t = new int[2];
					t[0] = i;
					t[1] = j;
					return t;
				}
			}
		}
		return null;
	}

	private Case[] movables() {
		Case[] m = new Case[4];
		for (int i = 0, c = 0; i < lignes; i++) {
			for (int j = 0; j < colones; j++) {
				if (isMovable(i, j) > 0) {
					m[c] = cases[i + 1][j + 1];
					c++;
				}
			}
		}
		return m;
	}

	private int isMovable(int i, int j) {
		i++;
		j++;
		if (cases[i][j].getState() == Case.FULL) {
			if (cases[i + 1][j].getState() == Case.EMPTY) {
				return cases[i + 1][j].getId();
			}
			if (cases[i - 1][j].getState() == Case.EMPTY) {
				return cases[i - 1][j].getId();
			}
			if (cases[i][j - 1].getState() == Case.EMPTY) {
				return cases[i][j - 1].getId();
			}
			if (cases[i][j + 1].getState() == Case.EMPTY) {
				return cases[i][j + 1].getId();
			}
		}
		return 0;
	}

	public void show() {
		for (int i = 0; i < lignes + 2; i++) {
			for (int j = 0; j < colones + 2; j++) {
				if (cases[i][j].getState() != Case.BORDER) {
					System.out.printf("%3d : %3d | ", cases[i][j].getId(),
							cases[i][j].getState());
				}
			}
			System.out.println("");
		}
	}

}
