package cyk.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.MultipleGradientPaint.CycleMethod;

import javax.swing.JDesktopPane;

/**
 * Erweitertes JDesktopPane mit anderem Hintergrund.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class Desktop extends JDesktopPane {
	private Paint paint;

	/**
	 * Erzeugt einen neuen Desktop.
	 */
	public Desktop() {
		int height = 400;

		Color color1 = new Color(.7f, .7f, .7f);
		Color color2 = new Color(.8f, .8f, .8f);

		paint = new LinearGradientPaint(0.0f, 0.01f, 150f, height, new float[] {
				0.0f, 0.8f }, new Color[] { color1, color2 }, CycleMethod.REFLECT);

	}

	/**
	 * Zeichnet den Desktop. Verwendet dabei einen Farbverlauf, statt dem standard Schwarz.
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}
