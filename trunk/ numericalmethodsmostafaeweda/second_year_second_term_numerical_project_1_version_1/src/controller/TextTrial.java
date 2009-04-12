package controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TextTrial {

	private Shell shell;
	private ArrayList<MyText> texts;
	private Point current;
	private MyText currentText;
	private Font font;
	private Canvas canvas;

	public static void main(String[] args) {
		new TextTrial().run();
	}

	public TextTrial() {
		texts = new ArrayList<MyText>();
		current = new Point(0, 0);
	}

	public void run() {
		Display display = new Display();
		shell = new Shell(display);
		createContents();
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

	private void createContents() {
		Display display = shell.getDisplay();
		font = new Font(display, "Comic Sans MS", 18, SWT.BOLD);
		shell.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.bottom = new FormAttachment(100, 0);
		canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.setLayoutData(formData);
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				Iterator<MyText> iter = texts.iterator();
				while (iter.hasNext())
					iter.next().draw(gc);
				if (currentText != null)
					currentText.draw(gc);
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				current.x = e.x;
				current.y = e.y;
				if (currentText != null) {
					texts.add(currentText);
					currentText = new MyText(font, new Point(e.x, e.y), "");
				}
				currentText = new MyText(font, new Point(e.x, e.y), "");
			}
		});
		canvas.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				currentText.addChar(e.character);
				canvas.redraw();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}});
	}

}
