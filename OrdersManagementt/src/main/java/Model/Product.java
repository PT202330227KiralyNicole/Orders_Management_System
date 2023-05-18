package Model;

/**
 * Clasa pentru produsul pe care clientul il comanda
 */

public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Constructor fara parametri
     */

    public Product(){
    }

    /**
     * <p>Constructor cu toti parametrii dati</p>
     * @param id
     * @param name
     * @param price
     * @param stock
     */
    public Product(int id, String name, int price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * <p>Constructor fara id</p>
     * @param name
     * @param price
     * @param stock
     */


    public Product(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * returneaza id-ul produsului
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     * seteaza id-ul produsului
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * returneaza numele produsului
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * seteaza numele produsului
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * returneaza pretul produsului
     * @return
     */

    public int getPrice() {
        return price;
    }

    /**
     * seteaza pretul produsului
     * @param price
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * returneaza stock-ul curent
     * @return
     */

    public int getStock() {
        return stock;
    }

    /**
     * seteaza stock-ul produsului
     * @param stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * functie pentru decrementarea stock-ului produsului cand s-a facut o comanda
     * @param quantity
     * @return
     */

    public int updateStock(int quantity){
        this.stock = this.stock-quantity;
        return this.stock;
    }

    /**
     * returneaza produsul pe care il comanda un client cu toate atributele sale
     * @return
     */

    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
    }
}
