package tech.horman.denys.server

import java.net.InetSocketAddress
import kotlin.coroutines.CoroutineContext

object GrpcContext {
    class ClientAddress(val address: InetSocketAddress) : CoroutineContext.Element {
        companion object Key : CoroutineContext.Key<ClientAddress>
        override val key: CoroutineContext.Key<ClientAddress>
            get() = Key
    }
}