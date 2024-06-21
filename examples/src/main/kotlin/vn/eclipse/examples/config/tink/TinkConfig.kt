package vn.eclipse.examples.config.tink

import com.google.crypto.tink.Aead
import com.google.crypto.tink.config.TinkConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import vn.eclipse.paymentconnector.utils.signature.tink.DefaultTinkToolkit
import vn.eclipse.paymentconnector.utils.signature.tink.TinkToolkit
import java.io.File

@Configuration
class TinkConfig {
    private val masterKeySource: String = ""
    private val masterKeyPath: String = "classpath:tink-master-key.bin"

    init {
        TinkConfig.register()
    }

    @Bean
    fun tinkToolkit(): TinkToolkit {
        return DefaultTinkToolkit()
    }

    @Bean
    fun aeadMasterKey(tinkToolkit: TinkToolkit): Aead? {
        var masterKey: Aead? = null

        val masterKeyFile = getFileFromPath(masterKeyPath)
        if (masterKeyFile != null) {
            val keysetHandle = tinkToolkit.readBinaryKeysetHandle(masterKeyFile)
            masterKey = keysetHandle.getPrimitive(Aead::class.java)
        }

        return masterKey
    }

    fun getFileFromPath(path: String?): File? {
        if (path.isNullOrBlank()) {
            return null
        }

        val file: File = if (path.startsWith("classpath:")) {
            val classPathResource = ClassPathResource(path.substring("classpath:".length))
            classPathResource.file
        } else {
            File(path)
        }
        return file
    }
}