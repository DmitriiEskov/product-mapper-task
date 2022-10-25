package service

import model.ProductPropertyResult
import model.ProductPropertySource

class ProductService {

    fun getPurchasedProductList(productCodeList: List<String>,
                                productPropertyMap: Map<String, ProductPropertySource>):
            List<ProductPropertyResult> {
        return mapProductCodeToPurchasedItem(productCodeList, productPropertyMap)
            .values
            .toList()
            .sortedBy { it.version }
    }

    private fun mapProductCodeToPurchasedItem(productCodeList: List<String>,
                                              productPropertyMap: Map<String, ProductPropertySource>):
            MutableMap<String, ProductPropertyResult> {

        val resultMap = mutableMapOf<String, ProductPropertyResult>()
        productCodeList
            .map { Pair(it, productPropertyMap[it] ?: ProductPropertySource() ) }
            .forEach {
                resultMap.getOrPut(it.first) { ProductPropertyResult(it.second.version, it.second.edition, 0) }
                    .quantity++
            }
        return resultMap
    }
}