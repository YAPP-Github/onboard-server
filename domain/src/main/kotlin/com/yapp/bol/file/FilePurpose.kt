package com.yapp.bol.file

enum class FilePurpose(val accessLevel: FileAccessLevel) {
    GROUP_DEFAULT_IMAGE(FileAccessLevel.PUBLIC),
    GROUP_IMAGE(FileAccessLevel.PUBLIC),
    MATCH_IMAGE(FileAccessLevel.PUBLIC),
}
