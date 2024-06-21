package vn.eclipse.examples.config.feign.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import vn.eclipse.paymentconnector.worldfirst.WorldfirstClient
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentRequest
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentResponse

@FeignClient(
    name = "world-first",
    configuration = [WorldFirstFeignClientConfig::class],
)
interface WorldFirstFeignClient : WorldfirstClient {
    companion object {
        const val CREATE_CASHIER_PAYMENT: String = "/amsin/api/v1/business/create"
        const val INQUIRE_PAYMENT: String = "/amsin/api/v1/business/inquiryPayOrder"
        const val SIGNATURE_HEADER: String = "signature"
        const val CLIENT_ID_HEADER: String = "client-id"
        const val REQUEST_TIME_HEADER: String = "request-time"
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = [CREATE_CASHIER_PAYMENT],
        consumes = ["application/json"]
    )
    override fun createCashierPayment(
        @RequestBody createCashierPaymentRequest: CreateCashierPaymentRequest,
        @RequestHeader(value = SIGNATURE_HEADER) signature: String,
        @RequestHeader(value = CLIENT_ID_HEADER) clientId: String,
        @RequestHeader(value = REQUEST_TIME_HEADER) requestTime: String,
    ): CreateCashierPaymentResponse
}