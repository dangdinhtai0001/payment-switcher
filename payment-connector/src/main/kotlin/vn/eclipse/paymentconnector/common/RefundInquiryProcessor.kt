package vn.eclipse.paymentconnector.common

interface RefundInquiryProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}