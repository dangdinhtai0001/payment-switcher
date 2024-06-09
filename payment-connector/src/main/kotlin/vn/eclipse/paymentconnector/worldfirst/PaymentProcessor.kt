package vn.eclipse.paymentconnector.worldfirst

interface PaymentProcessor<RequestType, ResponseType> {
    fun process(req: RequestType): ResponseType
}