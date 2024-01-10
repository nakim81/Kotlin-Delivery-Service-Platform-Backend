package org.delivery.common.error

enum class ErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String
) : ErrorCodeIfs {

    OK(200, 200, "Success"),
    BAD_REQUEST(400, 400, "Bad Request "),
    SERVER_ERROR(500, 500, "Server Error"),
    NULL_POINT(500, 512, "Null Point")

    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getErrorCode(): Int {
        return this.errorCode
    }

    override fun getDescription(): String {
        return this.description
    }
}