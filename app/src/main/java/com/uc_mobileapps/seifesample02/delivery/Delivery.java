package com.uc_mobileapps.seifesample02.delivery;

import android.content.Context;

import com.uc_mobileapps.seifesample02.delivery.schema.DeliverySchema;
import com.weebmeister.seife.anno.ForeignkeyDef;
import com.weebmeister.seife.anno.GeneratorOption;
import com.weebmeister.seife.anno.SeifeClass;
import com.weebmeister.seife.anno.SeifeEmbedded;
import com.weebmeister.seife.anno.SeifeField;

/**
 * Created by Klaus on 04.04.2017.
 */
@SeifeClass(
        generatorOptions = {
                GeneratorOption.BOCLASS, GeneratorOption.SCHEMA_PEER,
                GeneratorOption.DB_HELPER+"=com.uc_mobileapps.seifesample02.db.SupplierDB",
                GeneratorOption.DATA_PROVIDER+"=com.uc_mobileapps.seifesample02.provider.SupplierProvider"
        })
public class Delivery {

    @SeifeField(isPrimaryKey = true)
    private Long id;

    @SeifeField(foreignKey = @ForeignkeyDef(fkField = "product", refKeyField="id", refClass=Product.class))
    private Long productId;

    /**
     * Keep a reference to the product
     */
    private Product product;

    /**
     * Embed a copy of the name and description in case the product is changed afterwards
     */
    @SeifeEmbedded(prefix = "product_")
    private ProductData productAtOrderTime;

    public ProductData getProductAdOrderTime() {
        if (productAtOrderTime == null) {
            productAtOrderTime = new ProductData();
        }
        return productAtOrderTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }


    public ProductData getProductAtOrderTime() {
        return productAtOrderTime;
    }

    public void setProductAtOrderTime(ProductData productAtOrderTime) {
        this.productAtOrderTime = productAtOrderTime;
    }
	//[begin seife autogenerated@
	/** 
	 * Resolve the field via the foreign key and the provider. 
	 * By not caching the reference to the foreign object this can also be passed through IPC 
	 * @param context
	 * @return
	 */	public Product getProduct(Context context) {						
		return DeliverySchema.instance().resolveProductField(context, this);	
	}

 	/**
	 * Setter for the java reference.
	 * Also sets the technical foreign key reference fields based on the instance 
	 * @param fk if null the foreign key reference is cleared
	 */
	public void setProduct(Product fk) {
		this.product = fk;
		if (fk == null) {
			setProductId(null);
			return;
		}
		setProductId(fk.getId());
	}


	//@end seife autogenerated]
}
