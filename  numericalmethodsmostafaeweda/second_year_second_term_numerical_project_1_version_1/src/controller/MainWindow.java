package controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class MainWindow {

	private Shell shell;
	private Controller controller;

	public static void main(String[] args) {
		new MainWindow().run();
	}

	private void run() {
		Display display = new Display();
		shell = new Shell(display);
		shell.setLayout(new FormLayout());
		shell.setText("Equation Solver");
		controller = new Controller(this);
		createContents();
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		dispose(display);
	}

	private void dispose(Display display) {
		display.dispose();
	}

	private void createContents() {
		Composite composite = new Composite(shell, SWT.NONE);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.bottom = new FormAttachment(100, 0);
		composite.setLayoutData(formData);
		composite.setLayout(new GridLayout(4, false));
		CLabel equationLabel = new CLabel(composite, SWT.NONE);
		equationLabel.setText("Equation");
		final Text equation = new Text(composite, SWT.READ_ONLY);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		equation.setLayoutData(data);
		insertRow(composite, "X", Constants.X, true);
		insertRow(composite, "sin", Constants.SINE, false);
		insertRow(composite, "cos", Constants.COSINE, false);
		insertRow(composite, "e", Constants.COSINE, false);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 4;
		Composite controls = new Composite(composite, SWT.NONE);
		controls.setLayoutData(gridData);
		controls.setLayout(new GridLayout(5, true));

		final Button addTerm = new Button(controls, SWT.PUSH);
		addTerm.setText("ADD Term");
		addTerm.setEnabled(true);
		final Button plus = new Button(controls, SWT.PUSH);
		plus.setText("+");
		plus.setEnabled(false);
		final Button multiply = new Button(controls, SWT.PUSH);
		multiply.setText("*");
		multiply.setEnabled(false);
		final Button open = new Button(controls, SWT.PUSH);
		open.setText("(");
		open.setEnabled(false);
		final Button close = new Button(controls, SWT.PUSH);
		close.setText(")");
		close.setEnabled(false);
		final Button equals = new Button(controls, SWT.PUSH);
		equals.setText("=======");
		equals.setEnabled(false);

		plus.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.addSign(Constants.PLUS);
				equation.setText(equation.getText() + " + ");
				plus.setEnabled(false);
				multiply.setEnabled(false);
				open.setEnabled(false);
				close.setEnabled(false);
				addTerm.setEnabled(true);
				equals.setEnabled(false);
			}
		});
		multiply.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.addSign(Constants.MULTIPLY);
				equation.setText(equation.getText() + " * ");
				plus.setEnabled(false);
				multiply.setEnabled(false);
				open.setEnabled(false);
				close.setEnabled(false);
				addTerm.setEnabled(true);
				equals.setEnabled(false);
			}
		});
		open.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.addSign(Constants.OPEN_BRACE);
				equation.setText(equation.getText() + "(");
				plus.setEnabled(false);
				multiply.setEnabled(false);
				open.setEnabled(false);
				close.setEnabled(false);
				addTerm.setEnabled(true);
				equals.setEnabled(false);
			}
		});
		close.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.addSign(Constants.CLOSE_BRACE);
				equation.setText(equation.getText() + ")");
				plus.setEnabled(false);
				multiply.setEnabled(false);
				open.setEnabled(false);
				close.setEnabled(false);
				addTerm.setEnabled(true);
				equals.setEnabled(false);
			}
		});

		addTerm.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String coeff = (String) shell.getData("coeffText");
				String s = equation.getText();
				if (s.endsWith("+ ") &&  coeff.startsWith("-")) {
					s = s.substring(0, s.length() - 2);
				}
				equation.setText(s + controller.addTerm(coeff,
						(String)shell.getData("attrText"), (Integer)shell.getData("type")));
				plus.setEnabled(true);
				multiply.setEnabled(true);
				open.setEnabled(true);
				close.setEnabled(true);
				addTerm.setEnabled(false);
				equals.setEnabled(true);
			}
		});
		equals.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		shell.setData("coeffText", "0");
		shell.setData("attrText", "0");
		shell.setData("type", Constants.X);
	}

	private void insertRow(Composite composite, final String operationType, final int type, boolean enable) {
		final Button select = new Button(composite, SWT.RADIO);
		final Text coeff = new Text(composite, SWT.NONE);
		coeff.setText("0");
		coeff.setEnabled(enable);
		coeff.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CLabel label = new CLabel(composite, SWT.NONE);
		label.setText(operationType);
		final Text attr = new Text(composite, SWT.NONE);
		attr.setText("0");
		attr.setEnabled(enable);
		attr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		select.setSelection(enable);
		select.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				boolean selection = select.getSelection();
				coeff.setEnabled(selection);
				attr.setEnabled(selection);
				if (selection) {
					shell.setData("type", type);
				}
			}
		});
		coeff.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent arg0) {
				shell.setData("coeffText", coeff.getText());
			}});
		attr.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent arg0) {
				shell.setData("attrText", attr.getText());
			}});
	}
}
