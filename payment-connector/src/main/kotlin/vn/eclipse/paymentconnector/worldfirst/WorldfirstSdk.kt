package vn.eclipse.paymentconnector.worldfirst

import com.google.gson.Gson
import vn.eclipse.paymentconnector.worldfirst.model.CreateCashierPaymentRequest
import vn.eclipse.paymentconnector.worldfirst.model.CreateCashierPaymentResponse
import vn.eclipse.paymentconnector.worldfirst.model.InquirePaymentRequest
import vn.eclipse.paymentconnector.worldfirst.model.InquirePaymentResponse

class WorldfirstSdk(private val gson: Gson) : AbstractWorldfirstSdk(),
    PaymentProcessor<CreateCashierPaymentRequest, CreateCashierPaymentResponse>,
    PaymentInquiryProcessor<InquirePaymentRequest, InquirePaymentResponse> {

    private var customerId = "2188140338683180"
    private var paymentNotifyUrl = "http://www.sample.com"
    private var clientId = "5J5YBT2C2Y5LPN09049"
    private var requestUrlEndpoint = "/amsin/api/v1/business/create"

    override fun process(req: CreateCashierPaymentRequest): CreateCashierPaymentResponse {
        val requestTime = requestTimeToString(transaction.creationTimestamp)
        val httpMethod = "POST"
        val requestBodyStr = gson.toJson(req)

        val signature = generateSignature(requestTime, httpMethod, requestUrlEndpoint, requestBodyStr, clientId)
    }

    override fun process(req: InquirePaymentRequest): InquirePaymentResponse {
        TODO("Not yet implemented")
    }
}