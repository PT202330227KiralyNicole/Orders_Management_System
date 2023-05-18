package Model;

/**
 *Clasa pentru clientul care plaseaza o comanda
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Constructor fara parametri
     */

    public Client() {
    }

    /**
     * <p>Constructor cu toti parametrii dati</p>
     * @param id
     * @param name
     * @param address
     * @param email
     * @param age
     */

    public Client(int id, String name, String address, String email, int age) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }
    /**
     * <p>Constructor fara id</p>
     * @param name
     * @param address
     * @param email
     * @param age
     */

    public Client(String name, String address, String email, int age) {
        super();
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * getter pentru id-ul clientului
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     * setter pt id-ul clientului
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter pt numele clientului
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * setter pt numele clientului
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter pt adresa clientului
     * @return
     */

    public String getAddress() {
        return address;
    }

    /**
     * setter pt adresa clientului
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter pt varsta clientului
     * @return
     */

    public int getAge() {
        return age;
    }

    /**
     * setter pt varsta clientului
     * @param age
     */

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter pt email-ul clientului
     * @return
     */

    public String getEmail() {
        return email;
    }

    /**
     * setter pt email-ul clientului
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * metoda toString returneaza clientul impreuna cu atributele sale
     * @return
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }

}
