package com.uc_mobileapps.seifesample02;

import com.weebmeister.seife.anno.GeneratorOption;
import com.weebmeister.seife.anno.SeifeClass;
import com.weebmeister.seife.anno.SeifeEmbedded;
import com.weebmeister.seife.anno.SeifeField;

import android.content.Context;

/**
 * Shows the usually simple case of an embedded address.
 * Created by Klaus on 04.04.2017.
 */
@SeifeClass(
        generatorOptions = {
                GeneratorOption.BOCLASS, GeneratorOption.SCHEMA_PEER,
                GeneratorOption.DB_HELPER+"=SupplierDB",
                GeneratorOption.DATA_PROVIDER+"=SupplierProvider"
        })
public class Supplier {

    @SeifeField(isPrimaryKey = true)
    private Long id;

    @SeifeEmbedded(prefix="adrSource")
    private Address sourceAddress;

    @SeifeEmbedded(prefix="adrDelivery")
    private Address deliveryAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getSourceAddress() {
        if (sourceAddress == null) {
            sourceAddress = new Address();
        }
        return sourceAddress;
    }

    public void setSourceAddress(Address sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Address getDeliveryAddress() {
        if (deliveryAddress == null) {
            deliveryAddress = new Address();
        }
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}