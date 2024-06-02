import com.dignicate.zero_2024_kmp.data.sample.ApiService
import com.dignicate.zero_2024_kmp.data.sample.TodoDto
import com.dignicate.zero_2024_kmp.domain.spla.BukiRepository

class BukiRepositoryImpl(private val apiService: ApiService) : BukiRepository {
    override suspend fun getBukiData(): TodoDto {
        return apiService.getTodoData()
    }
}
