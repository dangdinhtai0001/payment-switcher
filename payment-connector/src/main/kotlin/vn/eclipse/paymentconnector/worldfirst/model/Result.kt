package vn.eclipse.paymentconnector.worldfirst.model

data class Result(
    val resultCode: String,
    val resultStatus: String,
    val resultMessage: String?,
) {
    enum class ResultStatusValue {
        S, F, U
    }
}