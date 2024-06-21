package vn.eclipse.paymentconnector.common

interface RefundProcessor <RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}