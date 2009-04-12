package controller;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

public class MyText {

	private String text;
	private Point position;
	private Font font;
	
	public MyText(Font font, Point position, String text) {
		this.font = font;
		this.position = position;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void draw(GC gc) {
		gc.setFont(font);
		gc.drawString(text, position.x, position.y);
	}

	public void addChar(char character) {
		text = text+character;
	}
}
