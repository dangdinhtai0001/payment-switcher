package vn.eclipse.paymentconnector.utils.signature.tink

import com.google.crypto.tink.Aead
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureEngine
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureManager
import java.util.concurrent.ConcurrentHashMap

class TinkSignatureManager(
    private val masterKey: Aead,
    private val tinkToolkit: TinkToolkit,
) : DigitalSignatureManager {
    private val engines: MutableMap<String, DigitalSignatureEngine> = ConcurrentHashMap()

    override fun remove(name: String) {
        engines.remove(name)
    }

    override fun getOrCreate(name: String): DigitalSignatureEngine {
        synchronized(lock = engines) {
            if (engines.containsKey(name)) {
                return engines.getValue(name)
            }

            val engine = createTinkEngine()
            engines[name] = engine!!

            return engine
        }
    }

    override fun get(name: String): DigitalSignatureEngine? {
        synchronized(lock = engines) {
            return engines[name]
        }
    }

    override fun clear() {
        engines.clear()
    }

    private fun createTinkEngine(): TinkSignatureEngine? {
        return null
    }

    private fun createTinkEngine(keyData: ByteArray): TinkSignatureEngine {
        val keysetHandle = tinkToolkit.readBinaryKeysetHandle(keyData, masterKey)

        return TinkSignatureEngine(keysetHandle)
    }
}