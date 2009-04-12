

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

	private Display display;
	private Shell shell;
	private boolean infocus = false;
	private Point currentLoc;
	private String currentString;

	public Test() {
		this.currentLoc = new Point(10, 10);
	}

	public static void main(String[] args) {
		new Test().run();
	}

	public void run() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Text editor trial");

		createContents();

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

	private void createContents() {
		shell.setLayout(new FormLayout());
		final Canvas can = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 10);
		data.left = new FormAttachment(0, 10);
		data.right = new FormAttachment(100, -10);
		data.bottom = new FormAttachment(100, -10);
		can.setLayoutData(data);
		
		currentString = "";

		can.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				infocus = true;
			}

		});

		can.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13)
				{
					
					currentString += '\n';
				}
				else	
					currentString += (char) e.keyCode;
				System.out.println(e.keyCode);
				can.redraw();
			}
		});
		
		can.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawString(currentString, currentLoc.x, currentLoc.y);
			}

		});
	}

}
