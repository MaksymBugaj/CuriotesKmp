package data.mapper.category

import data.db.entity.category.CurioteCategoryEntity
import data.mapper.base.DataMapper
import domain.model.category.Category

class CategoryMapper : DataMapper<CurioteCategoryEntity, Category> {
    override fun mapToData(domainModel: Category): CurioteCategoryEntity = CurioteCategoryEntity(
        id = domainModel.id,
        name = domainModel.name
    )

    override fun mapToDomain(data: CurioteCategoryEntity): Category = Category(
        id = data.id,
        name = data.name
    )
}