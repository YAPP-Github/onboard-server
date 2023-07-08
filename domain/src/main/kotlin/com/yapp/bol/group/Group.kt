package com.yapp.bol.group

import com.yapp.bol.InvalidGroupDescriptionException
import com.yapp.bol.InvalidGroupNameException
import com.yapp.bol.InvalidGroupOrganizationException

@JvmInline
value class GroupId(val value: Long)

data class Group(
    override val id: GroupId = GroupId(0),
    override val name: String,
    override val description: String,
    override val organization: String?,
    override val profileImageUrl: String = DEFAULT_PROFILE_IMAGE_URL,
    override val accessCode: String = generateAccessCode(),
) : GroupBasicInfo {
    init {
        if (name.length > MAX_NAME_LENGTH) {
            throw InvalidGroupNameException
        }
        if (description.length > MAX_DESCRIPTION_LENGTH) {
            throw InvalidGroupDescriptionException
        }

        if (organization?.length ?: 0 > MAX_ORGANIZATION_LENGTH) {
            throw InvalidGroupOrganizationException
        }
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
    }
}

interface GroupBasicInfo {
    val id: GroupId
    val name: String
    val description: String
    val organization: String?
    val profileImageUrl: String
    val accessCode: String
}
