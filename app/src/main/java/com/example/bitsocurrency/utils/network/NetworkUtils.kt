package com.example.bitsocurrency.utils.network

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException
import java.nio.channels.IllegalBlockingModeException

object NetworkUtils {
    suspend fun isNetworkAvailable():Boolean = coroutineScope {
        return@coroutineScope try {
            val socket = Socket()
            val address = InetSocketAddress("8.8.8.8", 53)
            val result = kotlin.runCatching {
                socket.connect(address, 1500)
                socket.close()
            }
            result.isSuccess
        }
        catch (e: Exception) { false }
        catch (e: IOException) { false }
        catch (e: SocketTimeoutException) { false }
        catch (e: IllegalBlockingModeException) { false }
        catch (e: IllegalArgumentException) { false }
    }
}