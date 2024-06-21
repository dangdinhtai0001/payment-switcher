package vn.eclipse.paymentconnector.worldfirst

import com.google.gson.Gson
import vn.eclipse.paymentconnector.common.PaymentInquiryProcessor
import vn.eclipse.paymentconnector.common.PaymentProcessor
import vn.eclipse.paymentconnector.models.PaymentMetadata
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureEngine
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureManager
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentRequest
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentResponse
import vn.eclipse.paymentconnector.worldfirst.models.InquirePaymentRequest
import vn.eclipse.paymentconnector.worldfirst.models.InquirePaymentResponse

class WorldfirstSdk(
    private val gson: Gson,
    private val worldfirstClient: WorldfirstClient,
    private val digitalSignatureManager: DigitalSignatureManager
) : AbstractWorldfirstSdk(),
    PaymentProcessor<CreateCashierPaymentRequest, CreateCashierPaymentResponse>,
    PaymentInquiryProcessor<InquirePaymentRequest, InquirePaymentResponse> {

    private var customerId = "2188140338683180"
    private var paymentNotifyUrl = "http://www.sample.com"
    private var clientId = "5J5YBT2C2Y5LPN09049"
    private var requestUrlEndpoint = "/amsin/api/v1/business/create"

    override fun process(req: CreateCashierPaymentRequest, metadata: PaymentMetadata): CreateCashierPaymentResponse {
        val requestTime = requestTimeToString(metadata.creationTimestamp)
        val httpMethod = "POST"
        val requestBodyStr = gson.toJson(req)

        val signature = generateSignature(requestTime, httpMethod, requestUrlEndpoint, requestBodyStr, clientId)

        return worldfirstClient.createCashierPayment(
            createCashierPaymentRequest = req,
            signature = signature,
            clientId = clientId,
            requestTime = requestTime
        )
    }

    override fun process(req: InquirePaymentRequest): InquirePaymentResponse {
        TODO("Not yet implemented")
    }

    override fun getDigitalSignatureEngine(name: String): DigitalSignatureEngine {
        return digitalSignatureManager.get(name)
            ?: throw IllegalArgumentException("DigitalSignatureEngine with name $name not found.")
    }

}