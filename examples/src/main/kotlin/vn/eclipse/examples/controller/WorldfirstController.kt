package vn.eclipse.examples.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vn.eclipse.examples.integration.Worldfirst
import vn.eclipse.paymentconnector.models.PaymentMetadata
import vn.eclipse.paymentconnector.worldfirst.models.Amount
import vn.eclipse.paymentconnector.worldfirst.models.CreateCashierPaymentRequest
import java.math.BigDecimal
import java.time.Instant

@RestController
class WorldfirstController(private val worldfirst: Worldfirst) {

    @GetMapping("/wf")
    fun createPayment() {

        worldfirst.worldfirstSdk?.process(
            req = createCashierPaymentRequest(),
            PaymentMetadata(creationTimestamp = Instant.now())
        )
    }

    private fun createCashierPaymentRequest(): CreateCashierPaymentRequest {
        val currency = "CNY"
        val amount = BigDecimal.valueOf(1500)
        val customerId = "2188140338683180"

        val orderGroup = CreateCashierPaymentRequest.OrderGroup(
            orderBuyer = CreateCashierPaymentRequest.Buyer("samtest1"),
            orderGroupDescription = "Cashier_payment",
            orderGroupId = "3AAPPJV2EHSNJ",
            orders = listOf(
                CreateCashierPaymentRequest.Order(
                    referenceOrderId = "3AAPPJV2EHSNJ",
                    orderDescription = "Cashier_payment",
                    orderTotalAmount = Amount(currency, amount),
                    transactionTime = Instant.now(),
                )
            ),
        )

        val payToDetails = listOf<CreateCashierPaymentRequest.PayToDetail>(
            CreateCashierPaymentRequest.PayToDetail(
                payToRequestId = "PAY030424691702654",
                payToAmount = Amount(currency, amount),
                payToMethod = CreateCashierPaymentRequest.PayToMethod(
                    customerId = customerId,
                    paymentMethodData = "",
                ),
                paymentNotifyUrl = "http://www.sample.com",
                referenceOrderId = "3AAPPJV2EHSNJ",
            )

        )


        return CreateCashierPaymentRequest(
            orderGroup = orderGroup,
            industryProductCode = "ONLINE_DIRECT_PAY",
            paymentRedirectUrl = "sabomall.com",
            payToDetails = payToDetails,
        )
    }
}