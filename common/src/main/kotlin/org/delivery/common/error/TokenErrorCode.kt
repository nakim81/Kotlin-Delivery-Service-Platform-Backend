package org.delivery.common.error

enum class TokenErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String
) : ErrorCodeIfs {

    INVALID_TOKEN(400, 2000, "Invalid Token"),
    EXPIRED_TOKEN(400, 2001, "Expired Token"),
    TOKEN_EXCEPTION(400, 2002, "Unknown Token Error"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "Authorization Header Token Not Found");

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