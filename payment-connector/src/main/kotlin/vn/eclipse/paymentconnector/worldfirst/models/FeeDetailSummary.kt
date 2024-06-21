package vn.eclipse.paymentconnector.worldfirst.models

data class FeeDetailSummary(
    val feeType: String,
    val paymentMethodType: String = "WALLET_WF",
    val feeAmount: Amount,
)