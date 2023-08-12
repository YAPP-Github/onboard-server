package com.yapp.bol.utils

import com.yapp.bol.NeedForceUpdateException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class ForceUpdateAspect {

    @Around("@annotation(com.yapp.bol.utils.ApiMinVersion)")
    fun checkForceUpdate(joinPoint: ProceedingJoinPoint): Any {
        try {
            val attributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
            val userAgent = attributes.request.getHeader("User-Agent")
            val appVersion = userAgent.split(" ")[0].split("/")
            val appType = appVersion[0]
            val version = appVersion[1]

            val signature = joinPoint.signature as MethodSignature
            val method = signature.method
            val annotation = method.getAnnotation(ApiMinVersion::class.java)

            if (appType.lowercase() == "android") {
                if (Version.of(annotation.androidVersion) > Version.of(version))
                    throw NeedForceUpdateException
            }
        } catch (e: Exception) {
            throw NeedForceUpdateException
        }

        return joinPoint.proceed()
    }

    class Version(
        val major: Int,
        val minor: Int,
        val patch: Int,
    ) : Comparable<Version> {
        override fun compareTo(other: Version): Int {
            if (major > other.major) return 1
            if (major < other.major) return -1

            if (minor > other.minor) return 1
            if (minor < other.minor) return -1

            return patch.compareTo(patch)
        }

        companion object {
            fun of(version: String): Version {
                val split = version.split(".")
                return Version(split[0].toInt(), split[1].toInt(), split[2].toInt())
            }
        }
    }
}
