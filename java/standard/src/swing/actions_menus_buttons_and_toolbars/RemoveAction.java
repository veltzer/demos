package swing.actions_menus_buttons_and_toolbars;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class RemoveAction extends AbstractAction implements ListSelectionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;


    public RemoveAction(JTable table) {
        this.table = table;
        table.getSelectionModel().addListSelectionListener(this);
        setEnabled(false);
        putValue(NAME, "Remove");
    }


    public void actionPerformed(ActionEvent ev) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        model.removeRow(row);
    }


    public void valueChanged(ListSelectionEvent ev) {
        setEnabled(table.getSelectedRowCount() == 1);
    }
}

