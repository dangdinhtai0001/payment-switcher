package vn.eclipse.paymentconnector.worldfirst

import vn.eclipse.paymentconnector.worldfirst.model.CreateCashierPaymentRequest
import vn.eclipse.paymentconnector.worldfirst.model.CreateCashierPaymentResponse
import vn.eclipse.paymentconnector.worldfirst.model.InquirePaymentRequest
import vn.eclipse.paymentconnector.worldfirst.model.InquirePaymentResponse

class WorldfirstSdk : AbstractWorldfirstSdk(),
    PaymentProcessor<CreateCashierPaymentRequest, CreateCashierPaymentResponse>,
    PaymentInquiryProcessor<InquirePaymentRequest, InquirePaymentResponse> {
    override fun process(req: CreateCashierPaymentRequest): CreateCashierPaymentResponse {
        TODO("Not yet implemented")
    }

    override fun process(req: InquirePaymentRequest): InquirePaymentResponse {
        TODO("Not yet implemented")
    }
}