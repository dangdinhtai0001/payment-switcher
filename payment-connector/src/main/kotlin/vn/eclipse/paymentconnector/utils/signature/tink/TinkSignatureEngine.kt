package vn.eclipse.paymentconnector.utils.signature.tink

import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.PublicKeySign
import com.google.crypto.tink.PublicKeyVerify
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureEngine
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureEngineType
import java.security.GeneralSecurityException

class TinkSignatureEngine(private val keysetHandle: KeysetHandle) : DigitalSignatureEngine {
    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    private val signer: PublicKeySign = keysetHandle.getPrimitive(PublicKeySign::class.java)
    private val verifier: PublicKeyVerify = keysetHandle.publicKeysetHandle.getPrimitive(PublicKeyVerify::class.java)

    override fun sign(data: ByteArray): ByteArray {
        return signer.sign(data)
    }

    override fun verify(data: ByteArray, signature: ByteArray): Boolean {
        try {
            verifier.verify(signature, data)

            return true
        } catch (e: GeneralSecurityException) {
            throw e
        }
    }

    override fun supports(delimiter: DigitalSignatureEngineType): Boolean {
        return delimiter == DigitalSignatureEngineType.TINK
    }
}