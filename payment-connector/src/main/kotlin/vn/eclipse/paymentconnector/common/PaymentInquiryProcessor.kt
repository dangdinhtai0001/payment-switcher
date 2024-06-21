package vn.eclipse.paymentconnector.common

interface PaymentInquiryProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}