package data.mapper.category

import data.db.entity.full.CurioteCategoryCombinedEntity
import data.mapper.base.DataMapper
import domain.model.category.CurioteCategoryCombined

class CurioteCategoryCombinedMapper: DataMapper<CurioteCategoryCombinedEntity, CurioteCategoryCombined> {
    override fun mapToData(domainModel: CurioteCategoryCombined): CurioteCategoryCombinedEntity {
        return CurioteCategoryCombinedEntity(
            curioteId = domainModel.curioteId,
            curioteCategoryId = domainModel.curioteCategoryId
        )
    }

    override fun mapToDomain(data: CurioteCategoryCombinedEntity): CurioteCategoryCombined {
        return CurioteCategoryCombined(
            curioteId = data.curioteId,
            curioteCategoryId = data.curioteCategoryId
        )
    }
}