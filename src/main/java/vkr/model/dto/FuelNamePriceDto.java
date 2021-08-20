package vkr.model.dto;

public class FuelNamePriceDto {
    private String name_fuel;
    private String price_fuel;


    public String getName_fuel() {
        return name_fuel;
    }

    public void setName_fuel(String name_fuel) {
        this.name_fuel = name_fuel;
    }

    public String getPrice_fuel() {
        return price_fuel;
    }

    public void setPrice_fuel(String price_fuel) {
        this.price_fuel = price_fuel;
    }

    public FuelNamePriceDto() {
    }

    public FuelNamePriceDto(String name_fuel, String price_fuel) {
        this.name_fuel = name_fuel;
        this.price_fuel = price_fuel;
    }
}
