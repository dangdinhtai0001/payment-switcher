package vn.eclipse.paymentconnector.worldfirst.models

import java.time.Instant

data class CreateCashierPaymentRequest(
    val orderGroup: OrderGroup,
    val payToDetails: List<PayToDetail>,
    val paymentRedirectUrl: String,
    val industryProductCode: String? = "ONLINE_DIRECT_PAY",
    val extendInfo: String? = null,
) {

    // region OrderGroup

    data class OrderGroup(
        val orderGroupId: String,
        val orderGroupDescription: String,
        val orderBuyer: Buyer,
        val orders: List<Order>
    )

    // endregion

    // region Buyer
    data class Buyer(
        val referenceBuyerId: String
    )

    // endregion

    // region Order
    data class Order(
        val referenceOrderId: String,
        val transactionTime: Instant,
        val orderDescription: String? = null,
        val orderTotalAmount: Amount,
        val orderFeeAmount: Amount? = null,
        val shippingInfo: Shipping? = null,
    )
    // endregion

    // region Shipping
    data class Shipping(
        val shippingName: UserName?,
        val shippingAddress: Address?,
        val shippingFee: Amount?,
        val shippingCarrier: String?,
    )
    // endregion

    // region Address
    data class Address(
        val region: String? = null,
        val state: String? = null,
        val city: String? = null,
        val address1: String? = null,
        val address2: String? = null,
        val zipCode: String? = null,
    )

    // region PayToDetail
    data class PayToDetail(
        val payToRequestId: String,
        val referenceOrderId: String,
        val payToAmount: Amount,
        val payToMethod: PayToMethod,
        val paymentNotifyUrl: String,
        val paymentExpiryTime: Instant? = null,
        val paymentTimeoutExpress: String? = null,
    )
    // endregion

    // region PayToMethod
    data class PayToMethod(
        val paymentMethodType: String? = "BALANCE",
        val paymentMethodDataType: String? = "PAYMENT_ACCOUNT_NO",
        val paymentMethodData: String,
        val customerId: String
    )
    // endregion
}