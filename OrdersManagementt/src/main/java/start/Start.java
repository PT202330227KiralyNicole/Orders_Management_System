package start;
import BusinessLogic.OrderBLL;
import DataAccess.LogDAO;
import DataAccess.OrderDAO;
import Model.Bill;
import Model.Orders;
import Presentation.ClientView;
import Presentation.Controller;
import Presentation.OrderView;
import Presentation.ProductView;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clasa pentru pornirea simularii
 */
public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {

        ClientView clientView = new ClientView();
        ProductView productView = new ProductView();
        OrderView orderView = new OrderView();

        Controller c = new Controller(clientView, productView, orderView);

//        Orders o = new Orders(4,1, 4, "08.06.2022", 9);
//        OrderBLL orderBLL = new OrderBLL();
//        orderBLL.updateOrder(o);


    }

}
