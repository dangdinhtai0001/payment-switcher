package vn.eclipse.paymentconnector.worldfirst

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

abstract class AbstractWorldfirstSdk {
    fun generateSignature(
        requestTime: String,
        httpMethod: String,
        requestUrlEndpoint: String,
        requestBodyStr: String,
        clientId: String,
    ): String {
        val signData: String = """
            $httpMethod $requestUrlEndpoint
            $clientId.$requestTime.$requestBodyStr
        """.trimIndent()

        val algorithm: String = "RSA256"
        val keyVersion: Int = 1

        return ""
    }

    fun requestTimeToString(requestTime: Instant?): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            .withZone(ZoneId.systemDefault())

        val formattedInstant = formatter.format(requestTime)

        return formattedInstant
    }
}