package vn.eclipse.paymentconnector.worldfirst

import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentRequest
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentResponse

interface WorldfirstClient {
    fun createCashierPayment(
        createCashierPaymentRequest: CreateCashierPaymentRequest,
        signature: String,
        clientId: String,
        requestTime: String,
    ): CreateCashierPaymentResponse
}