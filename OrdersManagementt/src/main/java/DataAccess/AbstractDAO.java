package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;

/**
 * <p>Clasa AbstractDAO este o clasa care utilizeaza parametrul T cu ajutorul caruia putem sa folosim clasele Client, Product si Orders in realizarea interogarilor</p>
 * @param <T>
 */
public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;


    /**
     * <p>Constructorul clasei AbstractDAO</p>
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * <p>Metoda createSelectQuery va realiza intr-un String interogarea SELECT * FROM table WHERE ... </p>
     * <p>field este coloana din baza de date, din tabelul corespunzator ales dupa care se va face cautarea</p>
     *
     * @param field
     * @return
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda createSelectAllQuery va realiza interogarea SELECT* FROM table si returneaza stringul format
     *
     * @return
     */

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * <p> Metoda createInsertQuery primeste ca parametru un obiect de tip T, T fiind Client, Product sau Orders</p>
     * <p> Aceasta metoda realizeaza interogarea INSERT INTO table VALUES(...)</p>
     *
     * @param t
     * @return
     */

    private String createInsertQuery(T t) {
        //INSERT INTO TABLE VALUES()
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()).append(" VALUES (");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            sb.append("?, ");
        }
        sb.replace(sb.length() - 2, sb.length(), " )");
        return sb.toString();
    }

    /**
     * <p> Metoda createUpdateQuery primeste ca parametru un obiect de tip T, T fiind Client, Product sau Orders</p>
     * <p> Aceasta metoda realizeaza interogarea UPDATE table SET col= val.. WHERE id = ..</p>
     *
     * @param t
     * @return
     */

    private String createUpdateQuery(T t) {
        //UPDATE TABLE SET COL=VAL WHERE ID=..
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName()).append(" SET ");
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append(fields[i].getName()).append(" = ?, ");
        }
        sb.replace(sb.length() - 2, sb.length(), " WHERE " + fields[0].getName() + " = ?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * <p> Metoda createDeleteQuery ne ajuta sa stergem o linie a tabelului in functie de id-ul introdus</p>
     *
     * @return
     */
    private String createDeleteQuery() {
        //DELETE FROM TABLE WHERE ID=..
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    /**
     * <p>Aceasta metoda foloseste interogarea SELECT* FROM table si ne ajuta sa gasim toate obiectele tabelului pe care le va returna</p>
     * <p>Executia se realizeaza cu ajutorul lui executeQuery()</p>
     *
     * @return
     */
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * <p>Metoda findById primeste ca parametru un id, apeleaza metoda createSelectQuery pentru a gasi un anumit obiect dupa id-ul sau, acesta fiind unic</p>
     * <p> Metoda returneaza obiectul gasit sau null in caz contrar</p>
     *
     * @param id
     * @return
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Metoda createObjects returneaza o lista de obiecte pe care le creeaza </p>
     *
     * @param resultSet
     * @return
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * <p> Metoda insert realizeaza executia interogarii create mai sus pentru insert</p>
     * <p>Aceasta returneaza obiectul inserat sau null in caz contrar</p>
     *
     * @param t
     * @return
     */

    public T insert(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                statement.setObject(i, f.get(t));
                i++;
            }
            statement.executeUpdate();
            //System.out.println(statement);
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p> Metoda update realizeaza executia interogarii create mai sus pentru update</p>
     * <p>Aceasta returneaza obiectul editat sau null in caz contrar</p>
     *
     * @param t
     * @return
     */

    public T update(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field f : t.getClass().getDeclaredFields()) {
                if (!f.getName().equals("id")) {
                    f.setAccessible(true);
                    statement.setObject(i, f.get(t));
                    i++;
                }
            }
            fields[0].setAccessible(true);
            statement.setObject(i, fields[0].get(t));
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Metoda update primeste ca parametru id-ul obiectului pe care il vom sterge din tabel</p>
     * <p>Aceasta metoda realizeaza executia metodei delete</p>
     *
     * @param id
     * @return
     */


    public boolean delete(int id) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    /**
     * <p>Metoda abstracta pentru a actualiza tabelele</p>
     * @param objects
     * @return
     */

    public Object[][] getTableData(List<T> objects) {
        if (!objects.isEmpty()) {
            Field[] fields = objects.get(0).getClass().getDeclaredFields();
            Object[][] data = new Object[objects.size()][fields.length];
            for (int i = 0; i < objects.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    try {
                        Field field = fields[j];
                        field.setAccessible(true);
                        Object value = field.get(objects.get(i));
                        data[i][j] = value;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data;
        }
        return null;
    }

    /**
     * <p>Metoda abstracta pentru a actualiza headerul din tabele</p>
     * @param objects
     * @return
     */

    public String[] getTableDataHeader(List<T> objects) {
            Field[] fields = objects.get(0).getClass().getDeclaredFields();
              String[] columnNames = new String[fields.length];
        for (int j = 0; j < fields.length; j++)
        {
                    Field field = fields[j];
                    field.setAccessible(true);
                        columnNames[j] = (field.getName());

                }

                return columnNames;

        }

}
