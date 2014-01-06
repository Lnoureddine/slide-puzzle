import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.Case;
import core.Grid;

public class Main extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] cases; //This is a comment
	private JPanel panel = new JPanel();
	private int l, c;
	Grid g;

	public Main() {
		l = 3;
		c = 3;
		cases = new JButton[l][c];
		g = new Grid(l, c);
		g.start();
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				cases[i][j] = new JButton(String.valueOf(g.getCase(i, j)
						.getId()));
				if (g.getCase(i, j).getState() == Case.EMPTY) {
					cases[i][j].setText("");
				}
				panel.add(cases[i][j]);
				cases[i][j].addActionListener(this);
			}
		}
		panel.setLayout(new GridLayout(l, c));
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		Main m = new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				if (e.getSource() == cases[i][j]) {
					if (g.userMove(i + 1, j + 1)) {
						System.out.println("Won");
					}
					for (int m = 0; m < l; m++) {
						for (int n = 0; n < c; n++) {
							cases[m][n].setText(String.valueOf(g.getCase(m, n)
									.getId()));
							if (g.getCase(m, n).getState() == Case.EMPTY) {
								cases[m][n].setText("");
							}
						}
					}
				}
			}
		}

	}
}
