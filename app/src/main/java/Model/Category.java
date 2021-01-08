package Model;

public class Category {
    private String menuId, image, name;

    public Category() {}

    public Category(String menuId, String image, String name) {
        this.menuId = menuId;
        this.image = image;
        this.name = name;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


