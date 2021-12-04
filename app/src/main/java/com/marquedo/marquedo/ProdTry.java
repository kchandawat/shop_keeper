package com.marquedo.marquedo;

import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductModelClass;

import java.util.Map;

public class ProdTry extends StoreTry
{
    Map<String, Map<String, ProductModelClass>> Mobiles;

    public Map<String, Map<String, ProductModelClass>> getMobiles() {
        return Mobiles;
    }

    public void setMobiles(Map<String, Map<String, ProductModelClass>> mobiles) {
        Mobiles = mobiles;
    }
}
