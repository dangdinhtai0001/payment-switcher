package vn.eclipse.paymentconnector.worldfirst

import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureEngine
import java.net.URLEncoder
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

abstract class AbstractWorldfirstSdk {

    protected fun generateSignature(
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

        val algorithm = "RSA256"
        val keyVersion = 1

        val digitalSignatureEngine = getDigitalSignatureEngine(name = clientId)

        val signatureByteArray: ByteArray = digitalSignatureEngine.sign(signData.toByteArray(Charsets.UTF_8))
        val signature = Base64.getEncoder().encodeToString(signatureByteArray)
        val signatureUrlSafe = URLEncoder.encode(signature, Charsets.UTF_8)
        val rs = "algorithm=$algorithm,keyVersion=$keyVersion,signature=$signatureUrlSafe"

        return rs
    }

    abstract fun getDigitalSignatureEngine(name: String): DigitalSignatureEngine;

    protected fun requestTimeToString(requestTime: Instant?): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            .withZone(ZoneId.systemDefault())

        val formattedInstant = formatter.format(requestTime)

        return formattedInstant
    }
}