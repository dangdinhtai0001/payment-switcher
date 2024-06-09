package vn.eclipse.paymentconnector.worldfirst.model

data class InquirePaymentRequest(
    val payToRequestIds: List<String>,
    val payToIds: List<String>
)