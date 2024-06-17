package vn.eclipse.paymentconnector.utils.signature.tink

import com.google.crypto.tink.*
import com.google.crypto.tink.signature.*
import com.google.crypto.tink.util.SecretBigInteger
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.security.Key
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPublicKey

class DefaultTinkToolkit : TinkToolkit {
    // region Read
    override fun readKeyFromPem(keyBufferedReader: BufferedReader, type: PemKeyType): Key? {
        return type.readKey(keyBufferedReader)
    }

    override fun readBinaryKeysetHandle(file: File): KeysetHandle {
        val reader = BinaryKeysetReader.withInputStream(file.inputStream())
        return CleartextKeysetHandle.read(reader)
    }

    override fun readBinaryKeysetHandle(bytes: ByteArray, masterKey: Aead): KeysetHandle {
        val reader = BinaryKeysetReader.withBytes(bytes)
        return KeysetHandle.read(reader, masterKey)
    }
    // endregion

    // region generateRsaSsaPss
    override fun generateRsaSsaPssPublicKey(keyBufferedReader: BufferedReader, type: PemKeyType): RsaSsaPssPublicKey {
        val publicKey: RSAPublicKey = type.readKey(keyBufferedReader) as RSAPublicKey

        return generateRsaSsaPssPublicKey(publicKey)
    }

    override fun generateRsaSsaPssPublicKey(publicKey: RSAPublicKey): RsaSsaPssPublicKey {
        return RsaSsaPssPublicKey.builder()
            .setModulus(publicKey.modulus)
            .setParameters(
                RsaSsaPssParameters.builder()
                    .setSigHashType(RsaSsaPssParameters.HashType.SHA512)
                    .setMgf1HashType(RsaSsaPssParameters.HashType.SHA512)
                    .setModulusSizeBits(publicKey.modulus.bitLength())
                    .setPublicExponent(publicKey.publicExponent)
                    .setVariant(RsaSsaPssParameters.Variant.NO_PREFIX)
                    .setSaltLengthBytes(64)
                    .build()
            )
            .build()
    }

    override fun generateRsaSsaPssPrivateKey(
        publicKey: RSAPublicKey,
        privateKeyBufferedReader: BufferedReader,
        privateKeyType: PemKeyType
    ): RsaSsaPssPrivateKey {
        val privateKey: RSAPrivateCrtKey =
            privateKeyType.readKey(privateKeyBufferedReader) as RSAPrivateCrtKey

        return generateRsaSsaPssPrivateKey(publicKey, privateKey)
    }

    override fun generateRsaSsaPssPrivateKey(
        publicKey: RSAPublicKey,
        privateKey: RSAPrivateCrtKey
    ): RsaSsaPssPrivateKey {
        val rsaSsaPssPublicKey = generateRsaSsaPssPublicKey(publicKey)

        return generateRsaSsaPssPrivateKey(privateKey, rsaSsaPssPublicKey)
    }

    override fun generateRsaSsaPssPrivateKey(
        privateKey: RSAPrivateCrtKey,
        rsaSsaPssPublicKey: RsaSsaPssPublicKey
    ): RsaSsaPssPrivateKey {
        return RsaSsaPssPrivateKey.builder()
            .setPublicKey(rsaSsaPssPublicKey)
            .setPrimes(
                SecretBigInteger.fromBigInteger(
                    privateKey.primeP, InsecureSecretKeyAccess.get()
                ),
                SecretBigInteger.fromBigInteger(
                    privateKey.primeQ, InsecureSecretKeyAccess.get()
                )
            )
            .setPrivateExponent(
                SecretBigInteger.fromBigInteger(
                    privateKey.privateExponent, InsecureSecretKeyAccess.get()
                )
            )
            .setPrimeExponents(
                SecretBigInteger.fromBigInteger(
                    privateKey.primeExponentP, InsecureSecretKeyAccess.get()
                ),
                SecretBigInteger.fromBigInteger(
                    privateKey.primeExponentQ, InsecureSecretKeyAccess.get()
                )
            )
            .setCrtCoefficient(
                SecretBigInteger.fromBigInteger(
                    privateKey.crtCoefficient, InsecureSecretKeyAccess.get()
                )
            )
            .build()
    }

    override fun generateRsaSsaPssPrivateKey(privateKey: RSAPrivateCrtKey): RsaSsaPssPrivateKey {
        val publicKey = RsaSsaPssPublicKey.builder()
            .setParameters(
                RsaSsaPssParameters.builder()
                    .setModulusSizeBits(privateKey.modulus.bitLength())
                    .setPublicExponent(privateKey.publicExponent)
                    .setSigHashType(RsaSsaPssParameters.HashType.SHA256)
                    .setMgf1HashType(RsaSsaPssParameters.HashType.SHA256)
                    .setVariant(RsaSsaPssParameters.Variant.NO_PREFIX)
                    .setSaltLengthBytes(32)
                    .build()
            )
            .setModulus(privateKey.modulus)
            .build()

        return generateRsaSsaPssPrivateKey(privateKey, publicKey)
    }

    override fun generateRsaSsaPssPrivateKey(keyBufferedReader: BufferedReader, type: PemKeyType): RsaSsaPssPrivateKey {
        val privateKey: RSAPrivateCrtKey =
            type.readKey(keyBufferedReader) as RSAPrivateCrtKey

        return generateRsaSsaPssPrivateKey(privateKey)
    }

    // endregion

    // region write
    override fun exportKeysetHandleToByteArray(keysetHandle: KeysetHandle, masterKey: Aead): ByteArray {
        val os = ByteArrayOutputStream()
        val keysetWriter = BinaryKeysetWriter.withOutputStream(os)

        keysetHandle.write(keysetWriter, masterKey)

        return os.toByteArray()
    }
    //endregion

    // region generateRsaSsaPkcs1
    override fun generateRsaSsaPkcs1PrivateKey(
        privateKey: RSAPrivateCrtKey,
        rsaSsaPkcs1PublicKey: RsaSsaPkcs1PublicKey
    ): RsaSsaPkcs1PrivateKey {
        return RsaSsaPkcs1PrivateKey.builder()
            .setPublicKey(rsaSsaPkcs1PublicKey)
            .setPrimes(
                SecretBigInteger.fromBigInteger(
                    privateKey.primeP, InsecureSecretKeyAccess.get()
                ),
                SecretBigInteger.fromBigInteger(
                    privateKey.primeQ, InsecureSecretKeyAccess.get()
                )
            )
            .setPrivateExponent(
                SecretBigInteger.fromBigInteger(
                    privateKey.privateExponent, InsecureSecretKeyAccess.get()
                )
            )
            .setPrimeExponents(
                SecretBigInteger.fromBigInteger(
                    privateKey.primeExponentP, InsecureSecretKeyAccess.get()
                ),
                SecretBigInteger.fromBigInteger(
                    privateKey.primeExponentQ, InsecureSecretKeyAccess.get()
                )
            )
            .setCrtCoefficient(
                SecretBigInteger.fromBigInteger(
                    privateKey.crtCoefficient, InsecureSecretKeyAccess.get()
                )
            )
            .build()
    }

    override fun generateRsaSsaPkcs1PrivateKey(privateKey: RSAPrivateCrtKey): RsaSsaPkcs1PrivateKey {
        // Build a RsaSsaPkcs1PrivateKey from a RSAPrivateCrtKey, without a RSAPublicKey.

        val rsaSsaPkcs1PublicKey = RsaSsaPkcs1PublicKey.builder()
            .setParameters(
                RsaSsaPkcs1Parameters.builder()
                    .setModulusSizeBits(privateKey.modulus.bitLength())
                    .setPublicExponent(privateKey.publicExponent)
                    .setHashType(RsaSsaPkcs1Parameters.HashType.SHA256)
                    .setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX)
                    .build()
            )
            .setModulus(privateKey.modulus)
            .build()

        return generateRsaSsaPkcs1PrivateKey(privateKey, rsaSsaPkcs1PublicKey)
    }

    override fun generateRsaSsaPkcs1PrivateKey(
        keyBufferedReader: BufferedReader,
        type: PemKeyType
    ): RsaSsaPkcs1PrivateKey {
        val privateKey: RSAPrivateCrtKey = type.readKey(keyBufferedReader) as RSAPrivateCrtKey

        return generateRsaSsaPkcs1PrivateKey(privateKey)
    }
    //endregion
}