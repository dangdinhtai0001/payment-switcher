package vn.eclipse.paymentconnector.utils.signature.tink

import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.PemKeyType
import com.google.crypto.tink.signature.RsaSsaPkcs1PrivateKey
import com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey
import com.google.crypto.tink.signature.RsaSsaPssPrivateKey
import com.google.crypto.tink.signature.RsaSsaPssPublicKey
import java.io.BufferedReader
import java.io.File
import java.security.Key
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPublicKey

interface TinkToolkit {
    // region Read

    fun readKeyFromPem(keyBufferedReader: BufferedReader, type: PemKeyType): Key?

    fun readBinaryKeysetHandle(file: File): KeysetHandle

    fun readBinaryKeysetHandle(bytes: ByteArray, masterKey: Aead): KeysetHandle
    // endregion

    // region  generateRsaSsaPss key
    fun generateRsaSsaPssPublicKey(keyBufferedReader: BufferedReader, type: PemKeyType): RsaSsaPssPublicKey
    fun generateRsaSsaPssPublicKey(publicKey: RSAPublicKey): RsaSsaPssPublicKey

    fun generateRsaSsaPssPrivateKey(
        publicKey: RSAPublicKey,
        privateKeyBufferedReader: BufferedReader,
        privateKeyType: PemKeyType
    ): RsaSsaPssPrivateKey

    fun generateRsaSsaPssPrivateKey(publicKey: RSAPublicKey, privateKey: RSAPrivateCrtKey): RsaSsaPssPrivateKey

    // Build a RsaSsaPssPrivateKey from a RsaSsaPssPublicKey and a RSAPrivateCrtKey.
    fun generateRsaSsaPssPrivateKey(
        privateKey: RSAPrivateCrtKey,
        rsaSsaPssPublicKey: RsaSsaPssPublicKey
    ): RsaSsaPssPrivateKey

    // Build a RsaSsaPssPrivateKey from a RSAPrivateCrtKey, without a RSAPublicKey.
    fun generateRsaSsaPssPrivateKey(privateKey: RSAPrivateCrtKey): RsaSsaPssPrivateKey

    fun generateRsaSsaPssPrivateKey(keyBufferedReader: BufferedReader, type: PemKeyType): RsaSsaPssPrivateKey
    // endregion

    // region write

    fun exportKeysetHandleToByteArray(keysetHandle: KeysetHandle, masterKey: Aead): ByteArray
    //endregion

    // region generateRsaSsaPkcs1
    fun generateRsaSsaPkcs1PrivateKey(
        privateKey: RSAPrivateCrtKey,
        rsaSsaPkcs1PublicKey: RsaSsaPkcs1PublicKey
    ): RsaSsaPkcs1PrivateKey

    fun generateRsaSsaPkcs1PrivateKey(privateKey: RSAPrivateCrtKey): RsaSsaPkcs1PrivateKey

    fun generateRsaSsaPkcs1PrivateKey(keyBufferedReader: BufferedReader, type: PemKeyType): RsaSsaPkcs1PrivateKey
    //endregion
}