package vn.eclipse.paymentconnector.worldfirst.model

import java.time.Instant

data class PayToSummary(
    val orderResult: CreateCashierPaymentResponse.OrderResult,
    val payToAmount: Amount,
    val payToRequestId: String,
    val payToId: String,
    val payToCreateTime: Instant
)