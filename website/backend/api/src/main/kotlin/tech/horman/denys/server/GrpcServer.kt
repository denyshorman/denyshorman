package tech.horman.denys.server

import io.grpc.Grpc.TRANSPORT_ATTR_REMOTE_ADDR
import io.grpc.Metadata
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.ServerCall
import io.grpc.kotlin.CoroutineContextServerInterceptor
import io.grpc.protobuf.services.ProtoReflectionService
import mu.KotlinLogging
import tech.horman.denys.api.grpc.NetworkUtilsService
import tech.horman.denys.api.grpc.PingPongService
import java.net.InetSocketAddress
import kotlin.coroutines.CoroutineContext

class GrpcServer(port: Int = 50051) {
    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(PingPongService())
        .addService(NetworkUtilsService())
        .addService(ProtoReflectionService.newInstance())
        .intercept(ClientIpInterceptor)
        .build()

    fun start() {
        server.start()
        logger.info("gRPC server has been started")
    }

    fun stop() {
        logger.info("Stopping gRPC server...")
        server.shutdown()
        server.awaitTermination()
        logger.info("gRPC server has been stopped")
    }

    fun awaitTermination() {
        server.awaitTermination()
    }

    companion object {
        private val logger = KotlinLogging.logger {}

        private object ClientIpInterceptor : CoroutineContextServerInterceptor() {
            override fun coroutineContext(call: ServerCall<*, *>, headers: Metadata): CoroutineContext {
                val ip = call.attributes[TRANSPORT_ATTR_REMOTE_ADDR] as? InetSocketAddress
                    ?: throw RuntimeException("Client's IP address is missing")
                return GrpcContext.ClientAddress(ip)
            }
        }
    }
}
