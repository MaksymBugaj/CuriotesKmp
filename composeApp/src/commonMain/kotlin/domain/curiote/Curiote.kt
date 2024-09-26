package domain.curiote

import domain.curiote.curioteLink.CurioteLink
import kotlinx.datetime.LocalDateTime


data class Curiote(
    val id: Long,
    val title: String?,
    val curiote: String?,
    val toCheck: Boolean,
    val created: LocalDateTime,
    val modified: LocalDateTime,
    val links: List<CurioteLink>?
)
