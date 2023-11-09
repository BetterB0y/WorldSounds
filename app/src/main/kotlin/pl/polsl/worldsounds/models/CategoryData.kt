package pl.polsl.worldsounds.models

import java.io.File

data class CategoryData(
    val id: Long,
    val name: String,
    val image: File,
    val assetsCounts: AssetsCountData,
) {
    companion object {
        fun default() = CategoryData(
            id = 0,
            name = "",
            image = File(""),
            assetsCounts = AssetsCountData(4, 4),
        )
    }
}