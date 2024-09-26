package data.mapper

import data.db.entity.CurioteEntity
import data.db.entity.full.CurioteFull
import data.mapper.base.DataMapper
import domain.curiote.Curiote

class CurioteMapper (
    private val curioteLinkMapper: CurioteLinkMapper
): DataMapper<CurioteFull, Curiote> {
    override fun mapToData(domainModel: Curiote): CurioteFull {
        return CurioteFull(
            curioteEntity = CurioteEntity(
                id = domainModel.id,
                title = domainModel.title,
                curiote = domainModel.curiote,
                toCheck = domainModel.toCheck,
                created = domainModel.created,
                modified = domainModel.modified
            ),
            links = domainModel.links?.map{ curioteLink ->
                curioteLinkMapper.mapToData(curioteLink, domainModel.id)
            }
        )
    }

    override fun mapToDomain(data: CurioteFull): Curiote {
        return Curiote(
            id = data.curioteEntity.id,
            title = data.curioteEntity.title,
            curiote = data.curioteEntity.curiote,
            toCheck = data.curioteEntity.toCheck,
            created = data.curioteEntity.created,
            modified = data.curioteEntity.modified,
            links = data.links?.map{ curioteLinkEntity ->
                curioteLinkMapper.mapToDomain(curioteLinkEntity)
            }
        )
    }
}