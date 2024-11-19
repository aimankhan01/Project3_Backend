import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Shop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopID;
    private String name;

    
    // Getters and Setters

    public Integer getShopID() {
        return shopID;
    }

    public String getName() {
        return name;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

}
