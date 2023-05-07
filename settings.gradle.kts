rootProject.name = "BoardGameRating"
include(
    "domain",
    "core",
    "port-in",
    "port-out",
    "adapter-in:web",
    "adapter-out:rdb",
)
