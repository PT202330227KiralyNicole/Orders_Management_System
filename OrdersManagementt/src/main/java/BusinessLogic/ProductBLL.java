package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p>Clasa ProductBLL din pachetul BusinessLogic</p>
 */

public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * <p> Constructorul clasei ProductBLL care initializeaza produsul din pachetul DataAccess</p>
     */

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     *<p> Metoda findProductById primeste ca parametru un id si cu ajutorul metodei findById din pachetul DataAccess va cauta produsul cu id-ul dat</p>
     * @param id
     * @return
     */

    public Product findProductById(int id) {
        Product p = productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return p;
    }

    /**
     * <p>Metoda findAllProducts va utiliza metoda findAll returnand toate produsele prezente in tabel</p>
     * @return
     */

    public List<Product> findAllProducts() {
        List<Product>  pList = productDAO.findAll();
        if (pList == null) {
            throw new NoSuchElementException("No products found!");
        }
        return pList;
    }

    /**
     * <p>Metoda insertProduct va primi ca parametru un produs, iar prin Reflection va insera un produs daca produsul nu e null</p>
     * @param product
     * @return
     */
    public Product insertProduct(Product product) {
        Product p = productDAO.insert(product);
        if (p == null) {
            throw new NoSuchElementException("Can t insert the client " + product.getId());
        }
        return p;
    }

    /**
     * <p>Metoda updateProduct primeste ca parametru un produs pe care va incerca sa-l actualizeze</p>
     * @param product
     * @return
     */

    public Product updateProduct(Product product) {
        Product p = productDAO.update(product);
        if (p == null) {
            throw new NoSuchElementException("Can t update the product " + product.getId());
        }
        return p;
    }

    /**
     * <p>Metoda deleteProduct va primi ca parametru un id si il va sterge din tabel</p>
     * @param id
     * @return
     */

    public boolean deleteProduct(int id) {
        boolean p = productDAO.delete(id);
        if (p == false) {
            throw new NoSuchElementException("Can t delete the product " + id);
        }
        return p;
    }

    /**
     * <p>Metoda updateStock se va folosi in cazul in care dorim sa punem o comanda, iar stock-ul trebuie scazut cu cantitatea pe care o comandam</p>
     * @param id
     * @param quantity
     */

    public void updateStock(int id, int quantity){
        Product p = productDAO.findById(id);
        if(p == null){
            throw new NoSuchElementException("The product was not found!!");
        }
        p.updateStock(quantity);
    }


}
