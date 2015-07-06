package za.co.standardbank.broders.property;

public class PropertyInfo {
    public int ID;
    public Double Amount;
    public String Street;
    public String Suburb;
    public String City;
    public String Province;
    public String Url;
    public Object GpsCoords;

    public PropertyInfo(int ID, Double amount, String street, String suburb, String city, String province, String url, Object gpsCoords) {
        this.ID = ID;
        Amount = amount;
        Street = street;
        Suburb = suburb;
        City = city;
        Province = province;
        Url = url;
        GpsCoords = gpsCoords;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getSuburb() {
        return Suburb;
    }

    public void setSuburb(String suburb) {
        Suburb = suburb;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Object getGpsCoords() {
        return GpsCoords;
    }

    public void setGpsCoords(Object gpsCoords) {
        GpsCoords = gpsCoords;
    }
}
