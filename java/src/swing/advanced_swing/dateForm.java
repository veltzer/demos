
import java.util.regex.Pattern;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dateForm.java
 *
 * Created on Jul 22, 2010, 11:47:39 AM
 */

/**
 *
 * @author veltzer
 */
public class dateForm extends javax.swing.JFrame {

    /** Creates new form dateForm */
    public dateForm() {
        initComponents();
        PlainDocument p=new PlainDocument() {
            Pattern p=Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

            private boolean check(String proposed) {
                if(p.matcher(proposed).find()) {
                    System.out.println("match for "+proposed);
                    return true;
                } else {
                    System.out.println("no match for "+proposed);
                    return false;
                }
            }

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength()!=0) {
                    String proposed=getText(0,offs)+str+getText(offs+str.length(),getLength()-offs-str.length());
                    if(check(proposed)) {
                       super.remove(offs,str.length());
                       super.insertString(offs, str, a);
                       if(offs==3 || offs==6) {
                           jTextField1.setCaretPosition(offs+2);
                       }
                    }
                } else {
                        super.insertString(offs, str, a);
                }

            }


            @Override
            public void remove(int offs, int len) throws BadLocationException {
                String proposed=getText(0,offs)+getText(offs+len,getLength()-offs-len);
                if(check(proposed)) {
                    super.remove(offs, len);
                }
            }

        };
        jTextField1.setDocument(p);
        jTextField1.setText("0000-00-00");
        jTextField1.setCaretPosition(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("jTextField1");

        jLabel1.setText("Please enter your birthdate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(202, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dateForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
