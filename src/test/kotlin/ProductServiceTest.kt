import model.ProductPropertyResult
import model.ProductPropertySource
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import service.ProductService
import kotlin.test.assertNull

class ProductServiceTest {

    private val productService: ProductService = ProductService()

    @DisplayName("""
        Inputs:
                List of products: ["CVCD", "SDFD", "DDDF", "SDFD"]
                Mappings: {
                    "CVCD": {"version": 1,"edition": "X"},"SDFD": {"version": 2,"edition":"Z"},"DDDF": {"version": 1}
                }
        ExpectedOutput:
                [
                    {"version":1,"edition":"X","quantity":1},
                    {"version":1,"quantity":1},
                    {"version":2,"edition":"Z","quantity":2}
                ]
    """)
    @Test
    fun whenGetPurchasedProductListThenSuccess() {
        val productCodeList: List<String> = listOf("CVCD", "SDFD", "DDDF", "SDFD")
        val productPropertyMap: Map<String, ProductPropertySource> = mapOf(
            Pair("CVCD", ProductPropertySource(1, "X")),
            Pair("SDFD", ProductPropertySource(2, "Z")),
            Pair("DDDF", ProductPropertySource(1))
        )

        val purchasedProductList: List<ProductPropertyResult> = productService.getPurchasedProductList(
            productCodeList, productPropertyMap
        )

        assert(purchasedProductList.size == 3)

        assert(purchasedProductList.get(0).version == 1)
        assert(purchasedProductList.get(0).edition.equals("X"))
        assert(purchasedProductList.get(0).quantity == 1)

        assert(purchasedProductList.get(1).version == 1)
        assertNull(purchasedProductList.get(1).edition)
        assert(purchasedProductList.get(1).quantity == 1)

        assert(purchasedProductList.get(2).version == 2)
        assert(purchasedProductList.get(2).edition.equals("Z"))
        assert(purchasedProductList.get(2).quantity == 2)
    }
}