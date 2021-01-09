package Model;

public class CartItem {
    private String id;
    private  isSave isSave;

    public CartItem(String id, Model.isSave isSave) {
        this.id = id;
        this.isSave = isSave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Model.isSave getIsSave() {
        return isSave;
    }

    public void setIsSave(Model.isSave isSave) {
        this.isSave = isSave;
    }
}
