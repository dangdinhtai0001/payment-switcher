package vn.eclipse.paymentconnector.worldfirst.models

data class PaymentDetailSummary(
    val customerId: String?,
    val customerName: UserName,
    val paymentAmount: Amount,
    val paymentMethodType: String,
    val paymentMethodMetadata: String?,
    val extendInfo: String?,
)