package vn.eclipse.paymentconnector.utils.signature

interface DigitalSignatureManager {
    fun remove(name: String)

    fun getOrCreate(name: String): DigitalSignatureEngine

    fun get(name: String): DigitalSignatureEngine?

    fun clear()
}