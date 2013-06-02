/*
 * Created on 22/08/2005
 *
 * TODO: To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fontpicker;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FontPicker {

	public static void main(String[] args) {
		FontPicker me = new FontPicker();
		me.run();
	}

	private Text fontName;
	private Text fontStyle;
	private Text fontSize;
	private Label fontShow;
	private Font font;

	public void run() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Font Selection");
		shell.setSize(400, 500);

		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		shell.setLayout(gl);

		// Row 1
		createFontNameColumn(shell);
		createFontStyleColumn(shell);
		createFontSizeColumn(shell);
		createButtonsColumn(shell);

		// Row 2
		new Label(shell, SWT.NONE); // placeholder

		Group fontShowBox = new Group(shell, SWT.SHADOW_NONE);
		GridData gd5 = new GridData();
		gd5.horizontalSpan = 3;
		gd5.heightHint = 40;
		gd5.horizontalAlignment = SWT.FILL;
		gd5.verticalAlignment = SWT.TOP;
		fontShowBox.setLayoutData(gd5);
		fontShowBox.setLayout(new FillLayout());
		fontShow = new Label(fontShowBox, SWT.CENTER);
		fontShow.setText("AaBbYyZz");

		refresh();

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		font.dispose();
		display.dispose();
	}

	public void refresh() {
		if (font != null) {
			font.dispose();
		}

		try {
			font = new Font(null, fontName.getText(), Integer.parseInt(fontSize
					.getText()), ((Integer) (fontStyle.getData())).intValue());
			fontShow.setFont(font);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void createFontNameColumn(Shell shell) {
		final Composite fontNameCol = new Composite(shell, SWT.NO_FOCUS);
		FillLayout fl1 = new FillLayout(SWT.VERTICAL);
		fl1.spacing = 3;
		fontNameCol.setLayout(fl1);
		GridData gd1 = new GridData();
		gd1.verticalAlignment = SWT.TOP;
		fontNameCol.setLayoutData(gd1);

		class FontNameButton {
			private Button btn;
			private final String font;

			FontNameButton(String aFont) {
				font = aFont;
				btn = new Button(fontNameCol, SWT.PUSH | SWT.LEFT);
				btn.setText(font);
				btn.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						fontName.setText(font);
						refresh();
					}
				});
			}
		}

		Label lbl = new Label(fontNameCol, SWT.LEFT);
		lbl.setText("Font");

		fontName = new Text(fontNameCol, SWT.SINGLE | SWT.BORDER);
		fontName.setText("Times New Roman");

		new FontNameButton("Arial");
		new FontNameButton("Times New Roman");
		new FontNameButton("Courier New");
	}

	private void createFontStyleColumn(Shell shell) {
		final Composite fontStyleCol = new Composite(shell, SWT.NO_FOCUS);
		FillLayout fl2 = new FillLayout(SWT.VERTICAL);
		fl2.spacing = 3;
		fontStyleCol.setLayout(fl2);
		GridData gd2 = new GridData();
		gd2.verticalAlignment = SWT.TOP;
		fontStyleCol.setLayoutData(gd2);

		class FontStyleButton {
			private Button btn;
			private final String styleName;
			private final int style;

			FontStyleButton(String aName, int aStyle, boolean selected) {
				styleName = aName;
				style = aStyle;
				btn = new Button(fontStyleCol, SWT.RADIO);
				btn.setText(styleName);
				btn.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						fontStyle.setText(styleName);
						fontStyle.setData(new Integer(style));
						refresh();
					}
				});
				btn.setSelection(selected);
			}
		}

		Label lbl = new Label(fontStyleCol, SWT.LEFT);
		lbl.setText("Font style");

		fontStyle = new Text(fontStyleCol, SWT.SINGLE | SWT.READ_ONLY
				| SWT.BORDER);
		fontStyle.setText("Normal");
		fontStyle.setData(new Integer(SWT.NORMAL));

		new FontStyleButton("Normal", SWT.NORMAL, true);
		new FontStyleButton("Bold", SWT.BOLD, false);
		new FontStyleButton("Italic", SWT.ITALIC, false);
		new FontStyleButton("Bold Italic", SWT.BOLD | SWT.ITALIC, false);
	}

	private void createFontSizeColumn(Shell shell) {
		final Composite fontSizeCol = new Composite(shell, SWT.NO_FOCUS);
		FillLayout fl3 = new FillLayout(SWT.VERTICAL);
		fl3.spacing = 3;
		fontSizeCol.setLayout(fl3);
		GridData gd3 = new GridData();
		gd3.verticalAlignment = SWT.TOP;
		fontSizeCol.setLayoutData(gd3);

		Label lbl = new Label(fontSizeCol, SWT.LEFT);
		lbl.setText("Size");

		fontSize = new Text(fontSizeCol, SWT.SINGLE | SWT.BORDER);
		fontSize.setText("10");
		fontSize.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				refresh();
			}
		});
		fontSize.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = true;
				if (e.character < ' ') {
					return;
				}
				try {
					Integer.parseInt(e.text);
				} catch (Exception ex) {
					e.doit = false;
				}
			}
		});
	}

	private void createButtonsColumn(final Shell shell) {
		final Composite buttonsCol = new Composite(shell, SWT.NO_FOCUS);
		FillLayout fl4 = new FillLayout(SWT.VERTICAL);
		fl4.spacing = 3;
		buttonsCol.setLayout(fl4);
		GridData gd4 = new GridData();
		gd4.verticalAlignment = SWT.TOP;
		buttonsCol.setLayoutData(gd4);

		new Label(buttonsCol, SWT.NONE); // placeholder

		SelectionAdapter closeAdapter = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		};

		Button ok = new Button(buttonsCol, SWT.PUSH | SWT.CENTER);
		ok.setText("OK");
		ok.addSelectionListener(closeAdapter);

		Button cancel = new Button(buttonsCol, SWT.PUSH | SWT.CENTER);
		cancel.setText("Cancel");
		cancel.addSelectionListener(closeAdapter);
	}
}
