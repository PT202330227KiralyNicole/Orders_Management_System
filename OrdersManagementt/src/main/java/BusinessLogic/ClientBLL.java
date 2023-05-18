package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * <p>ClientBLL este o clasa folosita pentru clientii care vor realiza comenzile, apartinand pachetului BusinessLogic</p>
 */
public class ClientBLL {

    private ClientDAO clientDAO;

    /**
     * <p>Constructorul clasei va initializa un obiect de tipul ClientDAO cu care vom lucra pe parcurs</p>
     */

    public ClientBLL() {

        clientDAO = new ClientDAO();
    }

    /**
     * <p>Metoda findClientById primeste ca parametru un id si va cauta acel client in baza de date cu ajutorul lui clientDAO</p>
     * @param id
     * @return
     */

    public  Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    /**
     * <p>Metoda findAllClients ne ajuta pentru a gasi toti clientii aflati in baza de date</p>
     * @return
     */

    public List<Client> findAllClients() {
        List<Client>  clList = clientDAO.findAll();
        if (clList == null) {
            throw new NoSuchElementException("No clients found!");
        }
        return clList;
    }

    /**
     * <p>Metoda insertClient este folosita la inserarea unui nou client in baza de date</p>
     * @param client
     * @return
     */

    public Client insertClient(Client client) {
        Client cl = clientDAO.insert(client);
        if (cl == null) {
            throw new NoSuchElementException("Can t insert the client " + client.getId());
        }
        return cl;
    }

    /**
     *<p>Metoda updateClient este folosita la editarea unui client in baza de date</p>
     * @param client
     * @return
     */

    public Client updateClient(Client client) {
        Client cl = clientDAO.update(client);
        if (cl == null) {
            throw new NoSuchElementException("Can t update the client " + client.getId());
        }
        return cl;
    }

    /**
     *  <p>Metoda deleteClient este folosita la stergerea unui client din baza de date</p>
     * @param id
     * @return
     */

    public boolean deleteClient(int id) {
        boolean cl = clientDAO.delete(id);
        if (cl == false) {
            throw new NoSuchElementException("Can t delete the client " +id);
        }
        return cl;
    }

//    public DefaultTableModel getTableDataClient(){
//       DefaultTableModel model = clientDAO.getTableData(findAllClients());
//       if(model == null){
//           throw new NoSuchElementException("No clients");
//       }
//        return model;
//
//    }


    public Object[][] getTableDataClient(){
        Object[][] model = clientDAO.getTableData(findAllClients());
        if(model == null){
            throw new NoSuchElementException("No clients");
        }
        return model;

    }

    public String[] getTableDataClientHeader(){
        String[] cl = clientDAO.getTableDataHeader(findAllClients());

        if(cl == null){
            throw new NoSuchElementException("No clients");
        }
        return cl;

    }

}
