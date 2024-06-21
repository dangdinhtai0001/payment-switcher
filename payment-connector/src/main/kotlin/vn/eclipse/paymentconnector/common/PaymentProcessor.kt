package vn.eclipse.paymentconnector.common

import vn.eclipse.paymentconnector.models.PaymentMetadata

interface PaymentProcessor<RequestType, ResponseType> {
    fun process(req: RequestType, metadata: PaymentMetadata): ResponseType
}