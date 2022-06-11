package tech.horman.denys.api.grpc

import com.google.protobuf.timestamp
import tech.horman.denys.api.grpc.pingpong.PingPongGrpcKt
import tech.horman.denys.api.grpc.pingpong.PingReply
import tech.horman.denys.api.grpc.pingpong.PingRequest
import tech.horman.denys.api.grpc.pingpong.pingReply
import java.time.Instant

class PingPongService : PingPongGrpcKt.PingPongCoroutineImplBase() {
    override suspend fun ping(request: PingRequest): PingReply {
        return pingReply {
            time = timestamp {
                val now = Instant.now()
                seconds = now.epochSecond
                nanos = now.nano
            }
        }
    }
}
