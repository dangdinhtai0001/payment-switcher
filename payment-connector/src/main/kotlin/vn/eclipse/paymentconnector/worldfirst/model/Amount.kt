package vn.eclipse.paymentconnector.worldfirst.model

import java.math.BigDecimal

data class Amount(
    val currency: String,
    val value: BigDecimal,
)