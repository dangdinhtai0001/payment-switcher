package vn.eclipse.paymentconnector.worldfirst.models

import java.time.Instant

data class PayToSummary(
    val orderResult: CreateCashierPaymentResponse.OrderResult,
    val payToAmount: Amount,
    val payToRequestId: String,
    val payToId: String,
    val payToCreateTime: Instant
)