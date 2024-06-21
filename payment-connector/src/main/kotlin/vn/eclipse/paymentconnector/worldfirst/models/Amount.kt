package vn.eclipse.paymentconnector.worldfirst.models

import java.math.BigDecimal

data class Amount(
    val currency: String,
    val value: BigDecimal,
)