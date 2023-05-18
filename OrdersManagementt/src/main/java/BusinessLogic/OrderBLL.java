package BusinessLogic;


import DataAccess.LogDAO;
import DataAccess.OrderDAO;
import Model.Bill;
import Model.Orders;
import Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p> Clasa OrderBLL este folosita pentru comenzi, apartinand pachetului BusinessLogic</p>
 */

public class OrderBLL {
    private OrderDAO orderDAO;
    private ProductBLL productBLL;

    /**
     * <p>Constructorul clasei OrderBLL este folosit pentru initializarea produsului si studentului din acelasi pahet si a comenzii din DataAccess pentru a utiliza query-urile</p>
     */

    public OrderBLL() {

        orderDAO = new OrderDAO();
        productBLL = new ProductBLL();

    }

    /**
     *<p>Clasa findOrderById primeste ca parametru un id si returneaza comanda cu id-ul trimis ca parametru</p>
     * @param id
     * @return
     */

    public Orders findOrderById(int id) {
        Orders o = orderDAO.findById(id);
        if (o == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return o;
    }

    /**
     * <p>Clasa findAllOrders returneaza lista de comenzi aflata in baza de date</p>
     * @return
     */

    public List<Orders> findAllOrders() {
        List<Orders> oList = orderDAO.findAll();
        if (oList == null) {
            throw new NoSuchElementException("No orders found!");
        }
        return oList;
    }

    /**
     * <p>Metoda insertOrder va primi ca parametru o comanda, iar prin Reflection va insera o comanda in tabel daca comanda nu e nula</p>
     * @param order
     * @return
     * @throws Exception
     */
    public Orders insertOrder(Orders order) throws Exception {
        productBLL.updateStock(order.getIdProduct(), order.getQuantity());
        Product p = productBLL.findProductById(order.getIdProduct());

            if (p.getStock() < order.getQuantity()) throw new Exception("There are not enough products");
           else {
                Product pr = new Product(p.getId(), p.getName(), p.getPrice(), p.updateStock(order.getQuantity()));
                p = productBLL.updateProduct(pr);
                if (p.getStock() == 0) {
                    productBLL.deleteProduct(p.getId());
                }
            }

        Orders o = orderDAO.insert(order);
        if (o == null) {
            throw new NoSuchElementException("Can t insert the order " + order.getId());
        }

        Bill bill = new Bill(o.getId(), o.getIdClient(),o.getIdProduct(),o.getDate(),o.getQuantity());
        LogDAO logDAO = new LogDAO();
        logDAO.insert(bill);

        return o;
    }

    /**
     *<p>Metoda updateOrder va primi ca parametru o comanda pe care o va edita si va actualiza in baza de date</p>
     * @param order
     * @return
     */

    public Orders updateOrder(Orders order) {
        Orders o= orderDAO.update(order);

        if (o == null) {
            throw new NoSuchElementException("Can t update the order " + order.getId());
        }
        Bill bill = new Bill(o.getId(), o.getIdClient(),o.getIdProduct(),o.getDate(),o.getQuantity());
        LogDAO logDAO = new LogDAO();
        logDAO.update(bill);
        return o;
    }

    /**
     * <p>Metoda deleteOrder va primi ca parametru id-ul unei comenzi, si va sterge acea comanda din tabel
     * @param id
     * @return
     */
    public boolean deleteOrder(int id){
        boolean o = orderDAO.delete(id);
        if (o == false) {
            throw new NoSuchElementException("Can t delete the order " + id);
        }
        return o  ;
    }
}
