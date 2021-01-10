package Model;

import java.util.ArrayList;

public class Order {
    private String Name;
    private String Phone;
    private String Address;
    private String Note;
    private ArrayList<String> foodId;
    private String paymentMethod;

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public Order(String name, String phone, String address, String note, ArrayList<String> foodId, String paymentMethod) {
        this.Name = name;
        this.Phone = phone;
        this.Address = address;
        this.Note = note;
        this.foodId = foodId;
        this.paymentMethod = paymentMethod;
    }

    public Order(String name, String phone, String address, String note, String paymentMethod ) {
        this.Name = name;
        this.Phone = phone;
        this.Address = address;
        this.Note =note;
        this.paymentMethod = paymentMethod;


    }

    public ArrayList<String> getFoodId() {
        return foodId;
    }

    public void setFoodId(ArrayList<String> foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

}
