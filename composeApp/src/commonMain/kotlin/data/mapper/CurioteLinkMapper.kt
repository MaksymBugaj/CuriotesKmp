package data.mapper

import data.db.entity.CurioteLinkEntity
import data.mapper.base.DataMapperWithParam
import domain.curiote.curioteLink.CurioteLink

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