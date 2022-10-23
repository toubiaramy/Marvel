package com.example.marvelapplication.vm.characterdetails.usecase

import com.example.marvelapplication.data.characters.models.Image
import com.example.marvelapplication.data.comics.Comic
import com.example.marvelapplication.data.comics.ComicDataContainer
import com.example.marvelapplication.data.comics.ComicDataWrapper
import com.example.marvelapplication.retrofit.Api
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response

class CharacterDetailsUseCaseImplTest {

    private val mockApi = mock(Api::class.java)
    private val mockData = mock(Call::class.java)
    private val mockExecute = mock(Response::class.java)
    private val mockCharacterDetail = "123"
    private val mockLimit = 3

    @Test
    fun `calling get comics when call is not successful returns no data`() {
        runBlocking {
            whenever(
                mockApi.getComics(
                    mockCharacterDetail,
                    mockLimit
                )
            ).thenReturn(mockData as Call<ComicDataWrapper>)
            whenever(mockApi.getComics(mockCharacterDetail, mockLimit).execute()).thenReturn(
                mockExecute as Response<ComicDataWrapper>
            )
            whenever(mockExecute.isSuccessful).thenReturn(false)

            val useCase = CharacterDetailsUseCaseImpl(mockApi)
            val comics = useCase.getComics(mockCharacterDetail)

            assert(comics is CharacterDetailsResult.NoData)
        }
    }

    @Test
    fun `calling get comics when having exception returns error`() {
        runBlocking {
            whenever(
                mockApi.getComics(
                    mockCharacterDetail,
                    mockLimit
                )
            ).thenReturn(mockData as Call<ComicDataWrapper>)
            whenever(mockApi.getComics(mockCharacterDetail, mockLimit).execute()).thenReturn(
                mockExecute as Response<ComicDataWrapper>
            )
            whenever(mockExecute.isSuccessful).thenReturn(true)

            val useCase = CharacterDetailsUseCaseImpl(mockApi)
            val comics = useCase.getComics(mockCharacterDetail)

            assert(comics is CharacterDetailsResult.Error)
        }
    }

    @Test
    fun `calling get comics when getting empty array from server returns no data`() {
        runBlocking {
            whenever(
                mockApi.getComics(
                    mockCharacterDetail,
                    mockLimit
                )
            ).thenReturn(mockData as Call<ComicDataWrapper>)
            whenever(mockApi.getComics(mockCharacterDetail, mockLimit).execute()).thenReturn(
                mockExecute as Response<ComicDataWrapper>
            )
            whenever(mockExecute.isSuccessful).thenReturn(true)

            val list: ArrayList<Comic> = ArrayList()
            whenever(mockExecute.body()).thenReturn(ComicDataWrapper(ComicDataContainer(list)))

            val useCase = CharacterDetailsUseCaseImpl(mockApi)
            val comics = useCase.getComics(mockCharacterDetail)

            assert(comics is CharacterDetailsResult.NoData)
        }
    }

    @Test
    fun `calling get comics when getting data from server returns success`() {
        runBlocking {
            whenever(
                mockApi.getComics(
                    mockCharacterDetail,
                    mockLimit
                )
            ).thenReturn(mockData as Call<ComicDataWrapper>)
            whenever(mockApi.getComics(mockCharacterDetail, mockLimit).execute()).thenReturn(
                mockExecute as Response<ComicDataWrapper>
            )
            whenever(mockExecute.isSuccessful).thenReturn(true)

            val list: ArrayList<Comic> = ArrayList()
            list.add(
                Comic(
                    1,
                    "name1",
                    "description1",
                    Date(),
                    Image("path1", ".ext")
                )
            )
            whenever(mockExecute.body()).thenReturn(ComicDataWrapper(ComicDataContainer(list)))

            val useCase = CharacterDetailsUseCaseImpl(mockApi)
            val comics = useCase.getComics(mockCharacterDetail)

            assert(comics is CharacterDetailsResult.Success)
        }
    }

    @Test
    fun `calling get comics when getting data from server returns list not empty`() {
        runBlocking {
            whenever(
                mockApi.getComics(
                    mockCharacterDetail,
                    mockLimit
                )
            ).thenReturn(mockData as Call<ComicDataWrapper>)
            whenever(mockApi.getComics(mockCharacterDetail, mockLimit).execute()).thenReturn(
                mockExecute as Response<ComicDataWrapper>
            )
            whenever(mockExecute.isSuccessful).thenReturn(true)

            val list: ArrayList<Comic> = ArrayList()
            list.add(
                Comic(
                    1,
                    "name1",
                    "description1",
                    Date(),
                    Image("path1", ".ext")
                )
            )
            whenever(mockExecute.body()).thenReturn(ComicDataWrapper(ComicDataContainer(list)))

            val useCase = CharacterDetailsUseCaseImpl(mockApi)
            val comics = useCase.getComics(mockCharacterDetail)

            assert(comics is CharacterDetailsResult.Success && comics.data.isNotEmpty())
        }
    }
}