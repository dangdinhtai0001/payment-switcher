package vn.eclipse.paymentconnector.worldfirst

interface PaymentInquiryProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}