package Model;

public class BestDeal {
    private String menuId;
    private String food_id;
    private String name;
    private String image;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public BestDeal() {

    }

    public BestDeal(String menuId, String food_id, String name, String image) {
        this.menuId = menuId;
        this.food_id = food_id;
        this.name = name;
        this.image = image;
    }

    public BestDeal(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
