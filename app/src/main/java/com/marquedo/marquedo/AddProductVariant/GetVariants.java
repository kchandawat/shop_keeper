package com.marquedo.marquedo.AddProductVariant;

import java.util.ArrayList;
import java.util.List;

public interface GetVariants {
    void getV(List<VariantData> variantDataList);

    void getVLSize(int size);

    void getVC(List<ColourCodes> colourData);

    void getVCLSize(int size);
}
