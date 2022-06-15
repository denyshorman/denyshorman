package tech.horman.denys

import tech.horman.denys.server.GrpcServer
import tech.horman.denys.server.GrpcWebProxyServer

fun main() {
    val grpcServerPort = System.getenv("GRPC_SERVER_PORT")?.toInt() ?: 50051
    val grpcWebProxyServerPort = System.getenv("GRPC_WEB_PROXY_SERVER_PORT")?.toInt() ?: 8080
    val grpcWebProxyServerPath = System.getenv("GRPC_WEB_PROXY_SERVER_PATH")
        ?: throw Exception("Please define a path to gRPC Web proxy server executable")

    val grpcServer = GrpcServer(grpcServerPort)

    val grpcWebProxyServer = GrpcWebProxyServer(
        proxyPath = grpcWebProxyServerPath,
        proxyPort = grpcWebProxyServerPort,
        grpcServerAddress = "127.0.0.1",
        grpcServerPort = grpcServerPort,
    )

    Runtime.getRuntime().addShutdownHook(
        Thread {
            grpcWebProxyServer.stop()
            grpcServer.stop()
        }
    )

    grpcServer.start()
    grpcWebProxyServer.start()

    grpcWebProxyServer.awaitTermination()
    grpcServer.awaitTermination()
}
