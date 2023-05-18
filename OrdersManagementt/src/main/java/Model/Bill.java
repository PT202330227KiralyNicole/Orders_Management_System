package Model;

/**
 * <p>Clasa Bill este create folosind Java Records, aceasta nu se mai poate actualiza odata ce va fi creat obiectul</p>
 * @param orderId id-ul comenzii
 * @param clientId id-ul clientului care a comandat
 * @param productId id-ul produsului pe care s-a facut factura
 * @param date  data realizarii comenzii
 * @param quantity cantitatea care a fost comandata
 */

public record Bill(int orderId, int clientId, int productId, String date, int quantity){
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------BILL---------------------------------------------");
        sb.append("ORDER: " + orderId + " CLIENT: " + clientId + " PRODUCT: "+ productId + " DATE: " + date + " QUANTITY: " + quantity);
        return sb.toString();

    }
}
