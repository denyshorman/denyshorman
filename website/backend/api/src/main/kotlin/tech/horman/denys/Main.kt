package tech.horman.denys

import io.grpc.ServerBuilder
import mu.KotlinLogging
import tech.horman.denys.api.grpc.PingPongService

fun main() {
    val logger = KotlinLogging.logger {}
    val grpcServerPort = System.getenv("GRPC_SERVER_PORT")?.toInt() ?: 50051

    val server = ServerBuilder
        .forPort(grpcServerPort)
        .addService(PingPongService())
        .build()

    Runtime.getRuntime().addShutdownHook(
        Thread {
            logger.info("Stopping gRPC server...")
            server.shutdown()
            server.awaitTermination()
            logger.info("gRPC server has been stopped")
        }
    )

    server.start()

    logger.info("gRPC server has been started on port $grpcServerPort")

    server.awaitTermination()
}
