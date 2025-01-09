package data.mapper.curiote

import data.db.entity.curiote.CurioteLinkEntity
import data.mapper.base.DataMapperWithParam
import domain.model.curiote.curioteLink.CurioteLink

class CurioteLinkMapper : DataMapperWithParam<CurioteLinkEntity, CurioteLink, Long>{
    override fun mapToData(domainModel: CurioteLink, param: Long): CurioteLinkEntity {
        return CurioteLinkEntity(
            id = domainModel.id,
            curioteLink = domainModel.link,
            curioteId = param
        )
    }

    override fun mapToDomain(data: CurioteLinkEntity): CurioteLink {
        return CurioteLink(
            data.id, data.curioteLink
        )
    }

}