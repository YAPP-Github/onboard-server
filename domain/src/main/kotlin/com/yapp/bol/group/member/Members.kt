package com.yapp.bol.group.member

class Members(private val members: MutableList<Member> = mutableListOf()) {
    private fun addOwner(userId: Long, nickname: String) {
        val owner = Member.createOwner(userId, nickname)

        members.add(owner)
    }

    fun toList(): List<Member> {
        return members.toList()
    }

    companion object {
        fun of(userId: Long, nickname: String): Members {
            val members = Members()

            members.addOwner(userId, nickname)

            return members
        }
    }
}
