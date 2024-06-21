package vn.eclipse.examples.config

import com.google.crypto.tink.Aead
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureManager
import vn.eclipse.paymentconnector.utils.signature.tink.TinkSignatureManager
import vn.eclipse.paymentconnector.utils.signature.tink.TinkToolkit

@Configuration
class ApplicationConfiguration {
    @Bean
    fun digitalSignatureEngineRegistry(
        masterKey: Aead,
        tinkToolkit: TinkToolkit
    ): DigitalSignatureManager {
        return TinkSignatureManager(masterKey, tinkToolkit)
    }
}