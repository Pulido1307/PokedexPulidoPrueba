package com.antonio.pulido.web

class RetrofitInstance {
    companion object{
        @JvmStatic
        var retrofitInstance: ApiService? = null
    }
}