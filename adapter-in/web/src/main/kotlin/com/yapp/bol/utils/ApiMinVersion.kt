package com.yapp.bol.utils

@Target(allowedTargets = [AnnotationTarget.FUNCTION])
annotation class ApiMinVersion(
    val androidVersion: String,
)
