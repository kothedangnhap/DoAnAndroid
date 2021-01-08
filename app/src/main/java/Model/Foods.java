package Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Foods {
    public int menuId,price,discount;
    public   String description,name,image;

    public Foods( int menuId, int price, int discount, String description, String name, String image) {

        this.menuId = menuId;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public Foods() {
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description", description);
        result.put("name", name);
        result.put("price", price);

        return result;
    }
}
