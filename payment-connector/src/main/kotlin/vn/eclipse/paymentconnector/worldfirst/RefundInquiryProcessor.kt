package vn.eclipse.paymentconnector.worldfirst

interface RefundInquiryProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}