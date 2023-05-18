package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <p> Clasa ClientView este interfata pentru inserarea, editarea, sau stergerea unui client din tabel</p>
 */

public class ClientView extends JFrame{
    private JButton addButton;
    private JButton editButton;
    private JButton delButton;
    private JButton viewButton;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel addrLabel;
    private JLabel emailLabel;
    private JLabel ageLabel;

    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField addrTextField;
    private JTextField emailTextField;
    private JTextField ageTextField;

    private JTable clientTable;

    /**
     * <p>Constructorul clasei ClientView in care se initializeaza tabelul pentru a vedea rezultatele din baza de date, butoanele, label-urile folosite</p>
     */

    public ClientView() {

       this.clientTable = new JTable();
        clientTable.setModel(new DefaultTableModel(
                new Object[][]{
                         {null,null,null,null,null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},



                }, new String[]{
                "New", "New", "New", "New", "New"

        }
        ));

        this.addButton = new JButton(" ADD CLIENT ");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        this.editButton = new JButton(" EDIT CLIENT ");
        editButton.setBackground(Color.BLACK);
        editButton.setForeground(Color.WHITE);
        this.delButton = new JButton(" DELETE CLIENT ");
        delButton.setBackground(Color.BLACK);
        delButton.setForeground(Color.WHITE);
        this.viewButton = new JButton(" VIEW ALL CLIENTS ");
        viewButton.setBackground(Color.BLACK);
        viewButton.setForeground(Color.WHITE);

        this.idLabel = new JLabel("ID:");
        this.nameLabel = new JLabel("NAME:");
        this.addrLabel = new JLabel("ADDRESS:");
        this.emailLabel = new JLabel("EMAIL:");
        this.ageLabel = new JLabel("AGE:");


        this.idTextField = new JTextField();
        this.idTextField.setPreferredSize(new Dimension(150, 20));
        this.nameTextField = new JTextField();
        this.nameTextField.setPreferredSize(new Dimension(150, 20));
        this.addrTextField = new JTextField();
        this.addrTextField.setPreferredSize(new Dimension(150, 20));
        this.emailTextField = new JTextField();
        this.emailTextField.setPreferredSize(new Dimension(150, 20));
        this.ageTextField = new JTextField();
        this.ageTextField.setPreferredSize(new Dimension(150, 20));


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.idLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.nameLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.addrLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.emailLabel);
        p1.add(Box.createRigidArea(new Dimension(0, 35)));
        p1.add(this.ageLabel);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.idTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.nameTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.addrTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.emailTextField);
        p2.add(Box.createRigidArea(new Dimension(0, 35)));
        p2.add(this.ageTextField);

        JPanel panelUp = new JPanel();
        panelUp.add(p1);
        panelUp.add(p2);

        JPanel panell = new JPanel();
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(panelUp);
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell.add(this.clientTable);


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
        this.setSize(600, 700);
        this.setTitle("CLIENT VIEW");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * <p>ActionListener pentru butonul de inserare a clientului</p>
     * @param actionListener
     */

    public void addAddButtonListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }

    /**
     * <p>ActionListener pentru butonul de editare a clientului</p>
     * @param actionListener
     */
    public void addEditButtonListener(ActionListener actionListener) {
        this.editButton.addActionListener(actionListener);
    }

    /**
     * <p>ActionListener pentru butonul de stergere a unui client</p>
     * @param actionListener
     */

    public void addDelButtonListener(ActionListener actionListener) {
        this.delButton.addActionListener(actionListener);
    }

    /**
     *  <p>ActionListener pentru butonul de vizualizare a intregului tabel actualizat</p>
     * @param actionListener
     */

    public void addViewButtonListener(ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }

    /**
     * <p> Metoda pentru afisarea mesajelor de eroare</p>
     * @param ms
     */

    public void showMessageError(String ms){
        JOptionPane.showMessageDialog(this, ms);
    }

    /**
     * <p>Metoda este folosita pentru a seta in tabel obiectul cu atributele sale</p>
     * @param o
     * @param row
     * @param col
     */
    public void setDataTable(Object o, int row, int col){
        this.clientTable.getModel().setValueAt(o, row, col);
    }

    /**
     * <p>Metoda este folosita pentru a reseta tabelul cu elementele pe null</p>
     */
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel)this.clientTable.getModel();
        for(int i = 1; i < model.getRowCount(); i++){
            for(int j = 0; j < model.getColumnCount(); j++){
                model.setValueAt(null, i ,j);
            }
        }
    }

    /**
     *<p> Metoda returneaza id-ul introdus sub forma de String</p>
     * @return
     */

    public String getIdInput(){
        return this.idTextField.getText();
    }
    /**
     *<p> Metoda returneaza numele clientului introdus sub forma de String</p>
     * @return
     */
    public String getNameInput(){
        return this.nameTextField.getText();
    }
    /**
     * <p>Metoda returneaza adresa clientului introdus sub forma de String</p>
     * @return
     */
    public String getAddrInput(){
        return this.addrTextField.getText();
    }
    /**
     * <p>Metoda returneaza email-ul clientului introdus sub forma de String</p>
     * @return
     */
    public String getEmailInput(){
        return this.emailTextField.getText();
    }
    /**
     * <p>Metoda returneaza varsta clientului introdus sub forma de String</p>
     * @return
     */
    public String getAgeInput(){
        return this.ageTextField.getText();
    }

}
