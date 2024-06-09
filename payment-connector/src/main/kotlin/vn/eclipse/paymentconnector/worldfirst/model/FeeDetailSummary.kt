package vn.eclipse.paymentconnector.worldfirst.model

data class FeeDetailSummary(
    val feeType: String,
    val paymentMethodType: String = "WALLET_WF",
    val feeAmount: Amount,
)