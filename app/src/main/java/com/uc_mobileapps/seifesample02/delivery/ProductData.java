package com.uc_mobileapps.seifesample02.delivery;

import com.weebmeister.seife.anno.SeifeEmbeddable;
import com.weebmeister.seife.anno.SeifeField;

import java.math.BigDecimal;

/**
 * Created by Klaus on 05.04.2017.
 */
@SeifeEmbeddable
public class ProductData {

    @SeifeField
    private String name;

    @SeifeField
    private BigDecimal price;

    @SeifeField
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
