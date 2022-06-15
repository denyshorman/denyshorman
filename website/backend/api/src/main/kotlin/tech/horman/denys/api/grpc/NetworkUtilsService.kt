package tech.horman.denys.api.grpc

import com.google.protobuf.ByteString
import com.google.protobuf.Empty
import tech.horman.denys.api.grpc.networkutils.MyIpReply
import tech.horman.denys.api.grpc.networkutils.NetworkUtilsGrpcKt
import tech.horman.denys.api.grpc.networkutils.myIpReply
import tech.horman.denys.server.GrpcContext
import kotlin.coroutines.coroutineContext

class NetworkUtilsService : NetworkUtilsGrpcKt.NetworkUtilsCoroutineImplBase() {
    override suspend fun getMyIp(request: Empty): MyIpReply {
        val clientAddress = coroutineContext[GrpcContext.ClientAddress]
            ?: throw RuntimeException("No client's IP in the context")

        return myIpReply {
            ip = ByteString.copyFrom(clientAddress.address.address.address)
        }
    }
}