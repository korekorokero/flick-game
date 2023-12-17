package main;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jframe = new JFrame("Flick");
				jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jframe.setContentPane(gamePanel);
				jframe.setResizable(false);
				jframe.pack();
				jframe.setLocationRelativeTo(null);
				jframe.setVisible(true);
			}
		});
	}
}
