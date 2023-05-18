package Presentation;
import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Controllerul folosit pentru a realiza functionalitatea pentru a insera, edita, sterge obiecte din tabel si vizualizarea tabelului</p>
 */
public class Controller {
    //TODO:...
    private ClientView clientView;
    private ProductView productView;
    private OrderView orderView;
    private ClientBLL clientBLL = new ClientBLL();
    private ProductBLL productBLL = new ProductBLL();
    private OrderBLL orderBLL = new OrderBLL();

    /**
     * <p>Constructorul clasei Controller care primeste ca parametrii interfetele pentru client, produs si comenzi</p>
     * <p>Tot aici se implementeaza metodele pentru fiecare buton din fiecare interfata</p>
     * @param clientView
     * @param productView
     * @param orderView
     */
    public Controller(ClientView clientView, ProductView productView, OrderView orderView){
        this.clientView = clientView;
        this.productView = productView;
        this.orderView = orderView;
        setTableValuesOrder(orderView, orderBLL);
        this.clientView.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int id = Integer.parseInt(clientView.getIdInput());
                String name = clientView.getNameInput();
                String address = clientView.getAddrInput();
                String email = clientView.getEmailInput();
                int age = Integer.parseInt(clientView.getAgeInput());
                if(name.equals("") || address.equals("") || email.equals("")){
                    throw new Exception("Please complete name/address/email");
                }else {
                    Client client = new Client(id, name, address, email, age);
                    Client out = clientBLL.insertClient(client);
                    if (out == null) {
                        clientView.showMessageError("Can t insert");
                    }
                }
                }catch(NumberFormatException ex){
                    clientView.showMessageError("Id/age is not an int");
                }
                catch(Exception exx){
                    clientView.showMessageError(exx.getMessage());
                }
            }
        });
        this.productView.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(productView.getIdInput());
                    String name = productView.getNameInput();
                    int price = Integer.parseInt(productView.getPriceInput());
                    int stock = Integer.parseInt(productView.getStockInput());
                    if(name.equals("")){
                        throw new Exception("Please introduce a name");
                    }else {
                        Product product = new Product(id, name, price, stock);
                        Product out = productBLL.insertProduct(product);
                        if (out == null) {
                            productView.showMessageError("Can t insert");
                        }
                    }
                }catch(NumberFormatException ex){
                    productView.showMessageError("Id/price/stock is not an int");
                }
                catch(Exception exx){
                    productView.showMessageError(exx.getMessage());
                }
            }
        });

        this.clientView.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{int ageInt = 0;
                    Client client1 = null;
                    int id = Integer.parseInt(clientView.getIdInput());
                    client1 = clientBLL.findClientById(id);
                    String name = clientView.getNameInput();
                    String address = clientView.getAddrInput();
                    String email = clientView.getEmailInput();
                     String age = clientView.getAgeInput();
                    if(name.equals("")){
                        name = client1.getName();
                    }
                    if(address.equals("")){
                        address = client1.getAddress();
                    }
                    if(email.equals("")){
                        email = client1.getEmail();
                    }
                    if(age.equals("")){
                        ageInt = client1.getAge();
                    }else{
                        ageInt = Integer.parseInt(age);
                    }
                    Client client2 = new Client(id, name, address, email, ageInt);
                    client1 = clientBLL.updateClient(client2);
                    if(client1 == null){
                        clientView.showMessageError("Can t update");
                    }
                }catch(NumberFormatException ex){
                    clientView.showMessageError("Id/age is not an int");
                }
            }
        });

        this.clientView.addViewButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTableValuesClient(clientView,clientBLL);
            }
        });
        this.productView.addViewButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTableValuesProduct(productView,productBLL);
            }
        });

        this.productView.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{int age = 0,  priceInt = 0, stockInt = 0;
                    Product product1 = null;
                    int id = Integer.parseInt(productView.getIdInput());
                    product1 = productBLL.findProductById(id);
                    String name = productView.getNameInput();
                     String price = productView.getPriceInput();
                    String stock = productView.getStockInput();
                    if(name.equals("")){
                        name = product1.getName();
                    }
                    if(price.equals("")){
                        priceInt = product1.getPrice();
                    }else{
                        priceInt = Integer.parseInt(price);
                    }
                    if(stock.equals("")){
                        stockInt = product1.getStock();
                    }else{
                        stockInt = Integer.parseInt(stock);
                    }
                    Product product2 = new Product(id, name, priceInt,stockInt);
                    product1 = productBLL.updateProduct(product2);
                    if(product1 == null){
                        productView.showMessageError("Can t update");
                    }
                }catch(NumberFormatException ex){
                    productView.showMessageError("Id/age is not an int");
                }
            }
        });



        this.clientView.addDelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = clientView.getIdInput();
                int idInt = 0;
                try{
                    idInt = Integer.parseInt(id);
                    if(clientBLL.deleteClient(idInt) == false){
                        clientView.showMessageError("Can t delete");
                    }
                }catch(NumberFormatException ex){
                    clientView.showMessageError("The input is not an int");
                }
            }
        });

        this.productView.addDelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = productView.getIdInput();
                int idInt = 0;
                try{
                    idInt = Integer.parseInt(id);

                    if(productBLL.deleteProduct(idInt) == false){
                        productView.showMessageError("Can t delete");
                    }
                }catch(NumberFormatException ex){
                    productView.showMessageError("The input is not an int");
                }
            }
        });

        this.orderView.addSendButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int id = Integer.parseInt(orderView.getIdInput());
                int idClient = Integer.parseInt(orderView.getIDClientInput());
                    int idProduct = Integer.parseInt(orderView.getIDProductInput());
                    String date = orderView.getDateInput();
                int quantity = Integer.parseInt(orderView.getQuantityInput());
                    Orders order = new Orders(id, idClient, idProduct, date, quantity);
                    Orders out = null;
                    try {
                        out = orderBLL.insertOrder(order);
                    }catch(Exception ex){
                        orderView.showMessageError(ex.getMessage());
                    }
                    if(out == null){
                        orderView.showMessageError("Can t insert");
                    }
                    setTableValuesOrder(orderView, orderBLL);
                }catch(NumberFormatException ex){
                    orderView.showMessageError("The input is not an int");
                }
            }
        });
    }

    /**
     * <p>Metoda setTableValuesClient este folosita pentru a seta valorile corespunzatoare in tabelul clientului, fiind actualizate de fiecare data la apasarea butonului View</p>
     * @param clientView
     * @param clientBLL
     */

    public void setTableValuesClient(ClientView clientView, ClientBLL clientBLL){
        clientView.clearTable();
        DefaultTableModel model = new DefaultTableModel(clientBLL.getTableDataClient(), clientBLL.getTableDataClientHeader());

      //  DefaultTableModel model = clientBLL.getTableDataClient();
        clientView.setDataTable(model);
    }

    /**
     * <p>Metoda setTableValuesProduct este folosita pentru a seta valorile corespunzatoare in tabelul produsului, fiind actualizate de fiecare data la apasarea butonului View</p>
     * @param productView
     * @param productBLL
     */

    public void setTableValuesProduct(ProductView productView, ProductBLL productBLL){
        DefaultTableModel model = new DefaultTableModel(productBLL.getTableDataProduct(), productBLL.getTableDataProductHeader());
        productView.setDataTable(model);

    }

    /**
     * <p>Metoda setTableValuesOrder este folosita pentru a seta valorile corespunzatoare in tabelul comenzilor, fiind actualizate de fiecare data la apasarea butonului View</p>
     * @param orderView
     * @param orderBLL
     */
    public void setTableValuesOrder(OrderView orderView, OrderBLL orderBLL){
       DefaultTableModel model = new DefaultTableModel(orderBLL.getTableDataOrder(), orderBLL.getTableDataOrderHeader());
        orderView.setDataTable(model);

    }
}
