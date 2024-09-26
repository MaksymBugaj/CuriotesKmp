package domain.curiote

interface CurioteDataSource {
    suspend fun insertCuriote(curiote: Curiote)
    suspend fun getCurioteById(id: Long): Curiote?
    suspend fun getAllCuriotes(): List<Curiote>
    suspend fun deleteCurioteById(id: Long)
}