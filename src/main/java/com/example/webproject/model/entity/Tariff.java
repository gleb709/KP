package com.example.webproject.model.entity;

import java.util.Objects;

public class Tariff implements Entity{
    private String name;
    private double price;
    private String info;
    private double discount;
    private String image;

    public Tariff(String name, double price, String info, double discount, String image) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.discount = discount;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double discount) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Tariff tariff = (Tariff) o;

        if (name == null) {
            if (tariff.name != null) {
                return false;
            }
        } else {
            if (!name.equals(tariff.name)) {
                return false;
            }
        }
        if (info == null) {
            if (tariff.info != null) {
                return false;
            }
        } else {
            if (!info.equals(tariff.info)) {
                return false;
            }
        }
        if (image == null) {
            if (tariff.image != null) {
                return false;
            }
        } else {
            if (!image.equals(tariff.image)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (name == null ? 0 : name.hashCode());
        result += 31 * result + (info == null ? 0 : info.hashCode());
        result += 31 * result + (image == null ? 0 : image.hashCode());
        result += 31 * Double.hashCode(discount);
        result += 31 * Double.hashCode(price);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(name);
        builder.append(" ");
        builder.append(price);
        builder.append(" ");
        builder.append(info);
        builder.append(" ");
        builder.append(discount);
        builder.append(" ");
        builder.append(image);

        return builder.toString();
    }
}
