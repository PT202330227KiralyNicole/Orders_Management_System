package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <p>Clasa OrderView este folosita pentru a crea interfata pentru comenzi </p>
 */

public class OrderView extends JFrame {
//TODO:...

    private JButton sendButton;
    private JLabel idLabel;
    private JLabel idClientLabel;
    private JLabel idProductLabel;
    private JLabel dateLabel;
    private JLabel quantityLabel;
    private JTextField idTextField;
    private JTextField idClientTextField;
    private JTextField idProductTextField;
    private JTextField dateTextField;
    private JTextField quantityTextField;
    private JTable orderTable;

    /**
     * <p>Constructorul clasei OrderView care initializeaza tabelul, butoanele si label-urile</p>
     */
    public OrderView() {

        this.orderTable = new JTable();
        orderTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},
                        {null,null,null,null,null},



                }, new String[]{
                "New", "New", "New", "New", "New"
        }
        ));

        this.sendButton = new JButton(" SEND ORDER ");
        sendButton.setBackground(Color.BLACK);
        sendButton.setForeground(Color.WHITE);

        this.idLabel = new JLabel("ID ORDER:");
        this.idClientLabel = new JLabel("ID CLIENT:");
        this.idProductLabel = new JLabel("ID PRODUCT:");
        this.dateLabel = new JLabel("DATE:");
        this.quantityLabel = new JLabel("QUANTITY:");


        this.idTextField = new JTextField();
        this.idTextField.setPreferredSize(new Dimension(150, 20));
        this.idClientTextField = new JTextField();
        this.idClientTextField.setPreferredSize(new Dimension(150, 20));
        this.idProductTextField = new JTextField();
        this.idProductTextField.setPreferredSize(new Dimension(150, 20));
        this.dateTextField = new JTextField();
        this.dateTextField.setPreferredSize(new Dimension(150, 20));
        this.quantityTextField = new JTextField();
        this.quantityTextField.setPreferredSize(new Dimension(150, 20));


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.idLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.idClientLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.idProductLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.dateLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.quantityLabel);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.idTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.idClientTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.idProductTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.dateTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.quantityTextField);
        JScrollPane scrollPane = new JScrollPane(orderTable);


        JPanel panelUp = new JPanel();
        panelUp.add(p1);
        panelUp.add(p2);
        JPanel panell = new JPanel();
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(panelUp);
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(scrollPane);


        JPanel auxiliar = new JPanel();
        auxiliar.add(this.sendButton);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        finalPanel.add(panell);
        //verticalContent2.add(Box.createRigidArea(new Dimension(0, 30)));
        finalPanel.add(auxiliar);


        this.pack();
        this.setContentPane(finalPanel);
        this.setSize(600, 600);
        this.setTitle("ORDER VIEW");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * <p>ActionListener pentru butonul Send</p>
     * @param actionListener
     */

    public void addSendButtonListener(ActionListener actionListener) {
        this.sendButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru afisarea erorilor</p>
     * @param ms
     */

    public void showMessageError(String ms){
        JOptionPane.showMessageDialog(this, ms);
    }

    /**
     * <p>Metoda este folosita pentru a seta in tabel obiectul cu atributele sale</p>
     */
    public void setDataTable(DefaultTableModel model){
        this.orderTable.setModel(model);
    }

//    public void setHeader(Object o, int row, int col){
//        this.orderTable.setValueAt(o,row,col);
//
//    }
    /**
     * <p>Metoda returneaza id-ul comenzii, introdus sub forma de String</p>
     * @return
     */

    public String getIdInput(){
        return this.idTextField.getText();
    }

    /**
     * <p>Metoda returneaza id-ul clientului, introdus sub forma de String</p>
     * @return
     */
    public String getIDClientInput(){
        return this.idClientTextField.getText();
    }

    /**
     * <p>Metoda returneaza id-ul produsului, introdus sub forma de String</p>
     * @return
     */
    public String getIDProductInput(){
        return this.idProductTextField.getText();
    }

    /**
     * <p>Metoda returneaza data comenzii, introdusa sub forma de String</p>
     * @return
     */
    public String getDateInput(){
        return this.dateTextField.getText();
    }

    /**
     * <p>Metoda returneaza cantitatea de produse, introdusa sub forma de String</p>
     * @return
     */
    public String getQuantityInput(){
        return this.quantityTextField.getText();
    }
}
