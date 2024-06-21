package vn.eclipse.paymentconnector.worldfirst.models

data class InquirePaymentRequest(
    val payToRequestIds: List<String>,
    val payToIds: List<String>
)