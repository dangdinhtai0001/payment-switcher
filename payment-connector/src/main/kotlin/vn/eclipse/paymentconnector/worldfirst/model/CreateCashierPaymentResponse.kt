package vn.eclipse.paymentconnector.worldfirst.model

data class CreateCashierPaymentResponse(
    val result: Result,
    val payToSummaries: List<PayToSummary>,
    val actionForm: String,
    val extendInfo: String? = null
) {
    data class OrderResult(
        val status: String,
        val statusMsg: String
    )

    data class ActionForm(
        val actionFormType: String,
        val method: String,
        val redirectUrl: String
    ) {
        companion object {
            const val REDIRECT_URL: String = "redirectUrl"
        }
    }
}