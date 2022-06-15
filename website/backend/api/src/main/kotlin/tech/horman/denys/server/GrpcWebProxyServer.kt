package tech.horman.denys.server

import mu.KotlinLogging

class GrpcWebProxyServer(
    proxyPath: String,
    proxyPort: Int,
    grpcServerAddress: String,
    grpcServerPort: Int,
    allowedOrigins: List<String> = emptyList(),
) {
    private val processBuilder: ProcessBuilder
    private var process: Process? = null

    init {
        val allowedOriginsStr = if (allowedOrigins.isEmpty()) {
            "--allow_all_origins"
        } else {
            "--allowed_origins=" + allowedOrigins.joinToString(",")
        }

        processBuilder = ProcessBuilder(
            proxyPath,
            allowedOriginsStr,
            "--server_http_debug_port=$proxyPort",
            "--backend_addr=$grpcServerAddress:$grpcServerPort",
            "--backend_tls_noverify",
            "--run_tls_server=false",
            "--use_websockets",
        ).redirectError(ProcessBuilder.Redirect.INHERIT)
    }

    fun start() {
        if (process == null) {
            process = processBuilder.start()
            logger.info("gRPC Web proxy server has been started")
        }
    }

    fun stop() {
        if (process != null) {
            logger.info("Stopping gRPC Web proxy server...")
            process!!.destroy()
            process!!.waitFor()
            logger.info("gRPC Web server proxy has been stopped")
        }
    }

    fun awaitTermination() {
        if (process != null) {
            process!!.waitFor()

            val exitCode = process!!.exitValue()

            if (exitCode > 1) {
                throw Exception("Proxy server stopped with error $exitCode")
            }
        }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}