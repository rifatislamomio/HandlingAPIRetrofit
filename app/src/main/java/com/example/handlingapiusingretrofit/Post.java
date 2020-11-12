package com.example.handlingapiusingretrofit;

public class Post {
    private String productname;
    private String barcode;
    private Integer id;
    private String message;


    public Post(String productname, String barcode) {
        this.productname = productname;
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProductname() {
        return productname;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
