package vn.eclipse.paymentconnector.utils.signature

import org.springframework.plugin.core.Plugin

interface DigitalSignatureEngine : Plugin<DigitalSignatureEngineType> {
    fun sign(data: ByteArray): ByteArray

    fun verify(data: ByteArray, signature: ByteArray): Boolean
}