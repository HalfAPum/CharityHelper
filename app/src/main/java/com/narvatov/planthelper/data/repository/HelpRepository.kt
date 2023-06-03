package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.remote.api.HelpApi
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.CreateTransaction
import com.narvatov.planthelper.models.remote.*
import com.narvatov.planthelper.models.remote.help.*
import com.narvatov.planthelper.models.remote.proposal.AllSearchQuery
import com.narvatov.planthelper.models.remote.proposal.SearchQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class HelpRepository(
    private val helpApi: HelpApi,
    private val fileRepository: FileRepository,
) {

    suspend fun createHelp(
        title: String, description: String, endDate: String, needs: List<Need>,
        tags: List<Pair<TagTitle, List<String>>>
    ) = withContext(Dispatchers.IO) {
        val image = fileRepository.uploadFile()

        val createHelpResponse = helpApi.createHelp(CreateNeed(title, description, endDate, needs,
            emptyList(),
        image = image)
        )

        helpApi.updateTags(
            UpdateHelpTags(
                eventId = createHelpResponse.id,
                tags.map { CreateTag(it.first, if (it.first == TagTitle.Location) it.second else it.second.filter { it.isBlank().not() }) }
            )
        )
    }

    suspend fun editHelp(
        title: String, description: String, status: String,
        tags: List<Pair<TagTitle, List<String>>>
    ) = withContext(Dispatchers.IO) {
        helpApi.editHelp(NavigationParams.helpDetailsItemId, EditHelpData(description, status, title))

        helpApi.updateTags(
            UpdateHelpTags(
                eventId = NavigationParams.helpDetailsItemId.toInt(),
                tags.map { CreateTag(it.first, if (it.first == TagTitle.Location) it.second else it.second.filter { it.isBlank().not() }) }
            )
        )
    }

    suspend fun getOwnHelps() = withContext(Dispatchers.IO) { helpApi.getOwnHelps() }

    suspend fun getPublicHelps() = withContext(Dispatchers.IO) { helpApi.getPublicHelps() }
    suspend fun getNotPublicHelps() = withContext(Dispatchers.IO) { helpApi.getPublicHelps(AllSearchQuery(takingPart = false)) }

    suspend fun searchHelps(
        query: String, order: String, sortField: String, status: String?, tags: List<Pair<TagTitle, List<String>>>
    ) = withContext(Dispatchers.IO) {
        val newTags = tags.filter { it.second.filter { it.isBlank().not() }.isEmpty().not() }
            .map { CreateTag(it.first, it.second.filter { it.isBlank().not() }, eventType = "help-event") }
        val searchQuery = SearchQuery(
            query = query, order = order, sortField = sortField,
            status = status, tags = newTags,
        )

        helpApi.searchHelps(searchQuery)
    }

    suspend fun getHelp(id: Long) = withContext(Dispatchers.IO) {
        val andProp = helpApi.getPublicHelps(AllSearchQuery(takingPart = false))
        val list = getPublicHelps().helpEvents.toMutableList()
        list.addAll(andProp.helpEvents)
        list.addAll(kotlin.runCatching { getOwnHelps().helpEvents }.getOrNull() ?: emptyList())
        return@withContext list.first { it.id == id }
    }

    suspend fun addComment(id: Long, text: String) = withContext(Dispatchers.IO) {
        helpApi.addComment(CreateComment(id, text))
    }

    suspend fun addTransaction(id: Long, text: String) = withContext(Dispatchers.IO) {
        helpApi.addTransaction(CreateTransaction(id, text))
    }


    suspend fun updateTransaction(transaction: Transaction, isApproved: Boolean? = null) = withContext(Dispatchers.IO) {
        val file = fileRepository.uploadFile()

        helpApi.updateTransaction(transaction.toUpdateTransaction(file, isApproved))
    }

}

fun Transaction.toUpdateTransaction(file: String?, isApproved: Boolean? = null) = UpdateHelpTransaction(
    id = id,
    status = transactionStatus,
    putNeeds = putNeeds,
    file = file,
    isApproved = isApproved
)