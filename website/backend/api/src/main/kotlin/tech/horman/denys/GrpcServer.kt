package tech.horman.denys

import io.grpc.Server
import io.grpc.ServerBuilder
import mu.KotlinLogging
import tech.horman.denys.api.grpc.PingPongService

class GrpcServer(private val port: Int = 50051) {
    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(PingPongService())
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
        val logger = KotlinLogging.logger {}
    }
}