package vn.eclipse.examples.integration

import com.google.gson.Gson
import org.springframework.stereotype.Component
import vn.eclipse.examples.config.feign.clients.WorldFirstFeignClient
import vn.eclipse.paymentconnector.utils.signature.DigitalSignatureManager
import vn.eclipse.paymentconnector.worldfirst.WorldfirstClient
import vn.eclipse.paymentconnector.worldfirst.WorldfirstSdk

@Component
class Worldfirst(
    private val gson: Gson,
    private val worldfirstClient: WorldfirstClient,
    private val digitalSignatureManager: DigitalSignatureManager
) {
    var worldfirstSdk: WorldfirstSdk? = null

    init {
        worldfirstSdk = WorldfirstSdk(
            gson = gson,
            worldfirstClient = worldfirstClient,
            digitalSignatureManager = digitalSignatureManager
        )
    }

}