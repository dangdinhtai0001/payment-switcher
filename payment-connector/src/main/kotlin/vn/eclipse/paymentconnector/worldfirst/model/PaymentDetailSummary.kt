package vn.eclipse.paymentconnector.worldfirst.model

data class PaymentDetailSummary(
    val customerId: String?,
    val customerName: UserName,
    val paymentAmount: Amount,
    val paymentMethodType: String,
    val paymentMethodMetadata: String?,
    val extendInfo: String?,
)