package com.example.hackathon.domain.response

class DataResponse<T>(code: Int, message: String, val data: T) : BaseResponse(code, message)
