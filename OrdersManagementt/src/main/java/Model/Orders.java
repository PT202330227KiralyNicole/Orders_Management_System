package Model;

/**
 * Clasa pentru comanda plasata de un client
 */

public class Orders {
    private int id;
    private int idClient;
    private int idProduct;
    private String date;
    private int quantity;

    /**
     * <p>Constructor fara parametri</p>
     */

    public Orders(){

    }
    /**
     * <p>Constructor cu toate atributele</p>
     * @param id
     * @param idClient
     * @param idProduct
     * @param date
     * @param quantity
     */

    public Orders(int id, int idClient, int idProduct, String date, int quantity){
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.date=date;
        this.quantity = quantity;
    }

    /**
     * <p>Constructor fara id</p>
     * @param idClient
     * @param idProduct
     * @param date
     * @param quantity
     */
    public Orders(int idClient, int idProduct, String date, int quantity){
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.date = date;
        this.quantity = quantity;
    }

    /**
     * returneaza id-ul comenzii
     * @return
     */


    public int getId() {
        return id;
    }

    /**
     * seteaza id-ul comenzii
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * returneaza id-ul clientului care a plasat comanda
     * @return
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * seteaza id-ul clientului care plaseaza comanda
     * @param idClient
     */

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * returneaza id-ul produsului comandat
     * @return
     */

    public int getIdProduct() {
        return idProduct;
    }

    /**
     * seteaza id-ul produsului pe care il comanda clientul
     * @param idProduct
     */

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * returneaza data plasarii comenzii
     * @return
     */

    public String getDate() {
        return date;
    }

    /**
     * seteaza data plasarii comenzii
     * @param date
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * returneaza cantitatea de produse pe care le comanda
     * @return
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     * seteaza cantitatea comandata de produse
     * @param quantity
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * returneaza comanda cu toate atributele sale
     * @return
     */

    public String toString() {
        return "Order [id=" + id + ", idClient=" + idClient + ", idProduct=" + idProduct + ", date=" + date + ", quantity=" + quantity + "]";
    }
}
