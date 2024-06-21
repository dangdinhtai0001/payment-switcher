package vn.eclipse.paymentconnector.worldfirst.models

data class InquirePaymentResponse(
    val result: Result,
    val payToSummaries: List<PayToSummary>,
    val paymentDetailSummaries: List<PaymentDetailSummary>,
    val feeDetailSummaries: List<FeeDetailSummary>
)