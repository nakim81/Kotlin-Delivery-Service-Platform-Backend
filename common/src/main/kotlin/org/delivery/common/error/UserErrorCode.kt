package org.delivery.common.error

enum class UserErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String
) : ErrorCodeIfs {

    USER_NOT_FOUND(400, 1404, "User Not Found"),

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