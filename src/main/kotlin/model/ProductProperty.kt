package model

data class ProductPropertySource(
    val version: Int = 0,
    val edition: String? = null
)

data class ProductPropertyResult(
    var version: Int,
    var edition: String? = null,
    var quantity: Int = 0
)