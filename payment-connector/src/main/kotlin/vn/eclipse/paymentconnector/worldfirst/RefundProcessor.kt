package vn.eclipse.paymentconnector.worldfirst

interface RefundProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}