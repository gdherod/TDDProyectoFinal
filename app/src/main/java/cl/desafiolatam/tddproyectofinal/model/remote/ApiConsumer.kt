package cl.desafiolatam.tddproyectofinal.model.remote

import cl.desafiolatam.tddproyectofinal.model.remote.pojo.BBWrapper
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BBAPI {
    @GET("/api/characters")
    suspend fun charactersList(): Response<BBWrapper>
}

class RetrofitClient {
    companion object {

        private const val BASE_URL = "https://www.breakingbadapi.com/api/"

        fun retrofitInstance(): BBAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BBAPI::class.java)
        }
    }
}
