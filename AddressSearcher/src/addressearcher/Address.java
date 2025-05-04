package addressearcher;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("logradouro")
    private String streetName;
    @SerializedName("cep")
    private String postalCode;
    @SerializedName("bairro")
    private String neighborhood;
    @SerializedName("localidade")
    private String localization;
    @SerializedName("estado")
    private String state;

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLocalization() {
        return localization;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getStreetName() {
        return streetName;
    }

    @Override
    public String toString() {
        return "Rua: " + this.streetName + "\n" + "Cep: " + this.postalCode
                + "\n" + "Cidade: " + this.localization + "\n" + "Bairro: " + this.neighborhood
                + "\n"+ "Estado: " + this.state + "\n\n";
    }
}
