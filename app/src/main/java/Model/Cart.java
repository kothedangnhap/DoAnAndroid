package Model;

public class Cart {
    private  int idUser;

    public Cart() {
    }

    public Cart(int idUser, int idFood) {
        this.idUser = idUser;
        this.idFood = idFood;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    private int idFood;
}
