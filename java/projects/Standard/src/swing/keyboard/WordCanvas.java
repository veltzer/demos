package swing.keyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WordCanvas extends JPanel {
	private HangManLogic hangManLogic;

	public WordCanvas(HangManLogic ihangManLogic) {
		hangManLogic = ihangManLogic;
		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(Font.decode("Courier New-BOLD-60"));

		char[] characters = hangManLogic.getCharacters();
		for (int i = 0; i < characters.length; ++i) {
			char displayChar = characters[i];
			if (!hangManLogic.characterIsGuessed(displayChar)) {
				displayChar = '_';
			}
			g2.drawString("" + displayChar, i * 50 + 20, 100);
		}
	}

}
