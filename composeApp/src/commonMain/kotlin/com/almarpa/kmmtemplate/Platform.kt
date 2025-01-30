package com.almarpa.kmmtemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform