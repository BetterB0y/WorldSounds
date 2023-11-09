package pl.polsl.worldsounds.models

data class AssetsCountData(
    val audios: Int,
    val images: Int,
) {
    fun areAssetsCorrect(numberOfRounds: Int): Boolean {
        if (audios <= 0 || images <= 0 || audios != images) return false
        if (numberOfRounds <= 4) {
            return audios >= 4
        }
        return audios >= numberOfRounds
    }
}