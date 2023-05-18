package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <p>Clasa ProductView este folosita pentru a insera, actualiza sau sterge un produs si afiseaza tabelul din baza de date dupa efectuarea unei operatii</p>
 */
public class ProductView extends JFrame {
    private JButton addButton;
    private JButton editButton;
    private JButton delButton;
    private JButton viewButton;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel stockLabel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField stockTextField;

    private JTable productTable;


    /**
     * <p>Constructorul clasei interefetei produselor, unde se initializeaza tabelul, butoanele si label-urile</p>
     */
    public ProductView() {
        this.productTable = new JTable();
//        productTable.setModel(new DefaultTableModel(
//                new Object[][]{
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//                        {null,null,null,null},
//
//                }, new String[]{
//                "New", "New", "New", "New"
//        }
//        ));

        this.addButton = new JButton(" ADD PRODUCT ");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        this.editButton = new JButton(" EDIT PRODUCT ");
        editButton.setBackground(Color.BLACK);
        editButton.setForeground(Color.WHITE);
        this.delButton = new JButton(" DELETE PRODUCT ");
        delButton.setBackground(Color.BLACK);
        delButton.setForeground(Color.WHITE);
        this.viewButton = new JButton(" VIEW ALL PRODUCTS ");
        viewButton.setBackground(Color.BLACK);
        viewButton.setForeground(Color.WHITE);

        this.idLabel = new JLabel("ID:");
        this.nameLabel = new JLabel("NAME:");
        this.priceLabel = new JLabel("PRICE:");
        this.stockLabel = new JLabel("STOCK:");

        this.idTextField = new JTextField();
        this.idTextField.setPreferredSize(new Dimension(150, 20));
        this.nameTextField = new JTextField();
        this.nameTextField.setPreferredSize(new Dimension(150, 20));
        this.priceTextField = new JTextField();
        this.priceTextField.setPreferredSize(new Dimension(150, 20));
        this.stockTextField = new JTextField();
        this.stockTextField.setPreferredSize(new Dimension(150, 20));

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.idLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.nameLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.priceLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.stockLabel);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.idTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.nameTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.priceTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.stockTextField);

        JScrollPane scrollPane = new JScrollPane(productTable);

        JPanel panelUp = new JPanel();
        panelUp.add(p1);
        panelUp.add(p2);
        JPanel panell = new JPanel();
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(panelUp);
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(scrollPane);

        JPanel verticalContent1 = new JPanel();
        verticalContent1.setLayout(new BoxLayout(verticalContent1, BoxLayout.Y_AXIS));
        verticalContent1.add(this.addButton);
        verticalContent1.add(Box.createRigidArea(new Dimension(0, 30)));
        verticalContent1.add(this.editButton);

        JPanel verticalContent2 = new JPanel();
        verticalContent2.setLayout(new BoxLayout(verticalContent2, BoxLayout.Y_AXIS));
        verticalContent2.add(Box.createRigidArea(new Dimension(0, 30)));
        verticalContent2.add(this.delButton);
        verticalContent2.add(Box.createRigidArea(new Dimension(0, 30)));
        verticalContent2.add(this.viewButton);

        JPanel auxiliar = new JPanel();
        auxiliar.add(verticalContent1);
        auxiliar.add(Box.createRigidArea(new Dimension(0, 30)));
        auxiliar.add(verticalContent2);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        finalPanel.add(panell);
        verticalContent2.add(Box.createRigidArea(new Dimension(0, 30)));
        finalPanel.add(auxiliar);


        this.pack();
        this.setContentPane(finalPanel);
        this.setSize(600, 650);
        this.setTitle("PRODUCT VIEW");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
    /**
     * <p>ActionListener pentru butonul de inserare al produsului</p>
     * @param actionListener
     */

    public void addAddButtonListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }
    /**
     * <p>ActionListener pentru butonul de editare al produsului</p>
     * @param actionListener
     */

    public void addEditButtonListener(ActionListener actionListener) {
        this.editButton.addActionListener(actionListener);
    }
    /**
     * <p>ActionListener pentru butonul de delete al produsului</p>
     * @param actionListener
     */


    public void addDelButtonListener(ActionListener actionListener) {
        this.delButton.addActionListener(actionListener);
    }

    /**
     * <p>ActionListener pentru butonul de vizualizare al tabelului produsului</p>
     * @param actionListener
     */

    public void addViewButtonListener(ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }

    /**
     * <p>Aceasta metoda se va folosi in cazul afisarii unei erori in cadrul interfetei</p>
     * @param ms
     */
    public void showMessageError(String ms){
        JOptionPane.showMessageDialog(this, ms);
    }

    /**
     * <p>Metoda este folosita pentru a seta in tabel obiectul cu atributele sale</p>
     */
    public void setDataTable(DefaultTableModel model){
        this.productTable.setModel(model);
    }    /**
     * <p>Metoda este folosita pentru a reseta tabelul cu elementele pe null</p>
     */
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel)this.productTable.getModel();
        for(int i = 1; i < model.getRowCount(); i++){
            for(int j = 0; j < model.getColumnCount(); j++){
                model.setValueAt(null, i ,j);
            }
        }
    }

    /**
     * <p>Metoda returneaza id-ul produsului, sub forma de String</p>
     * @return
     */
    public String getIdInput(){
        return this.idTextField.getText();
    }

    /**
     * <p>Metoda returneaza numele produsului, sub forma de String</p>
     * @return
     */
    public String getNameInput(){
        return this.nameTextField.getText();
    }

    /**
     * <p>Metoda returneaza pretul produsului sub forma de String</p>
     * @return
     */
    public String getPriceInput(){
        return this.priceTextField.getText();
    }

    /**
     * <p>Metoda returneaza stock-ul produsului, sub forma de String</p>
     * @return
     */
    public String getStockInput(){
        return this.stockTextField.getText();
    }


}
