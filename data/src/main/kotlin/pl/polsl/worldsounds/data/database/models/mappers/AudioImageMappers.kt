package pl.polsl.worldsounds.data.database.models.mappers

import pl.polsl.worldsounds.data.database.models.AudioEntity
import pl.polsl.worldsounds.data.database.models.ImageEntity
import pl.polsl.worldsounds.domain.models.AudioModel
import pl.polsl.worldsounds.domain.models.ImageModel


internal fun AudioEntity.toModel(): AudioModel = AudioModel(
    id = id,
    name = name,
    extension = extension
)

internal fun ImageEntity.toModel(): ImageModel = ImageModel(
    id = id,
    name = name,
    extension = extension
)