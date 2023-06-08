package com.yapp.bol.group

import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.Members

class Group(
    val id: Long,
    val name: String,
    val description: String,
    val organization: String,
    val profileImageUrl: String,
    val members: Members,
    val accessCode: String,
) {
    init {
        require(name.length <= MAX_NAME_LENGTH) { "그룹 이름은 $MAX_NAME_LENGTH 자 이내여야 합니다." }
        require(description.length <= MAX_DESCRIPTION_LENGTH) { "그룹 설명은 $MAX_DESCRIPTION_LENGTH 자 이내여야 합니다." }
        require(organization.length <= MAX_ORGANIZATION_LENGTH) { "그룹 소속은 $MAX_ORGANIZATION_LENGTH 자 이내여야 합니다." }
    }

    companion object {
        const val MAX_NAME_LENGTH = 14
        const val MAX_DESCRIPTION_LENGTH = 72
        const val MAX_ORGANIZATION_LENGTH = 15

        const val ACCESS_CODE_LENGTH = 6

        const val DEFAULT_PROFILE_IMAGE_URL =
            "https://github.com/YAPP-Github" +
                "/22nd-Android-Team-2-BE" +
                "/assets/46865281/2a3caefb-e0ab-4f60-b745-273a889f0c96" // FIXME: 임시 이미지

        private fun generateAccessCode(): String {
            val chars = ('A'..'Z') + ('0'..'9')

            return (1..ACCESS_CODE_LENGTH)
                .map { chars.random() }
                .joinToString("")
        }

        fun of(
            name: String,
            description: String,
            organization: String,
            profileImageUrl: String? = DEFAULT_PROFILE_IMAGE_URL,
        ): Group {
            val members = Members.of(
                Member(0, 0, "OWNER", name),
            )

            return Group(
                0, name, description, organization,
                profileImageUrl ?: DEFAULT_PROFILE_IMAGE_URL, members, ""
            )
        }

        fun of(
            name: String,
            description: String,
            organization: String,
            profileImageUrl: String?,
            ownerId: Long,
            nickname: String,
        ): Group {
            val members = Members.of(
                Member(0, 0, "OWNER", name),
            )
            val accessCode = generateAccessCode()

            return Group(
                0,
                nickname,
                description,
                organization,
                profileImageUrl ?: DEFAULT_PROFILE_IMAGE_URL,
                members,
                accessCode
            )
        }
    }
}
