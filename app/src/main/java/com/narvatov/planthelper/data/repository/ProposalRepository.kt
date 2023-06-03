package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.remote.api.ProposalApi
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.AcceptTransaction
import com.narvatov.planthelper.models.CreateTransaction
import com.narvatov.planthelper.models.remote.*
import com.narvatov.planthelper.models.remote.help.EditHelpData
import com.narvatov.planthelper.models.remote.help.UpdateHelpTags
import com.narvatov.planthelper.models.remote.proposal.AllSearchQuery
import com.narvatov.planthelper.models.remote.proposal.CreateProposal
import com.narvatov.planthelper.models.remote.proposal.SearchQuery
import com.narvatov.planthelper.models.remote.proposal.UpdateTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import java.lang.Exception

@Factory
class ProposalRepository(
    private val proposalApi: ProposalApi,
    private val fileRepository: FileRepository,
) {

    suspend fun createProposal(title: String, description: String, maxConcurrentRequests: Long, endDate: String, tags: List<Pair<TagTitle, List<String>>>) = withContext(Dispatchers.IO) {
        val image = fileRepository.uploadFile()

        proposalApi.createProposal(CreateProposal(title, description, maxConcurrentRequests, endDate, tags.map { CreateTag(it.first, if (it.first == TagTitle.Location) it.second else it.second.filter { it.isBlank().not() }) },
        image = image))
    }

    suspend fun getOwnProposals() = withContext(Dispatchers.IO) {
        kotlin.runCatching { proposalApi.getOwnProposals() }.getOrNull() }

    suspend fun getPublicProposals() = withContext(Dispatchers.IO) { proposalApi.getPublicProposals() }
    suspend fun getNotPublicProposals() = withContext(Dispatchers.IO) { proposalApi.getPublicProposals(AllSearchQuery(false)) }

    suspend fun searchProposals(
        query: String, order: String, sortField: String, status: String?, tags: List<Pair<TagTitle, List<String>>>
    ) = withContext(Dispatchers.IO) {
        val newTags = tags.filter { it.second.filter { it.isBlank().not() }.isEmpty().not() }
            .map { CreateTag(it.first, it.second.filter { it.isBlank().not() }) }
        val searchQuery = SearchQuery(
            query = query, order = order, sortField = sortField,
            status = status, tags = newTags,
        )

        proposalApi.searchProposals(searchQuery)
    }

    suspend fun checkNotification(id: Long) = withContext(Dispatchers.IO) {
        proposalApi.checkNotifications(NotificationsCheck(listOf(id)))
    }

    suspend fun getProposal(id: Long) = withContext(Dispatchers.IO) {
        val andProp = proposalApi.getPublicProposals(AllSearchQuery(takingPart = false))
        val list = getPublicProposals().proposalEvents.toMutableList()
            list.addAll(andProp.proposalEvents)
//            list.addAll(getOwnProposals()?.proposalEvents ?: emptyList())
        return@withContext list.first { it.id == id }
    }

    suspend fun addComment(id: Long, text: String) = withContext(Dispatchers.IO) {
        proposalApi.addComment(CreateComment(id, text))
    }

    suspend fun addTransaction(id: Long, text: String) = withContext(Dispatchers.IO) {
        proposalApi.addTransaction(CreateTransaction(id, text))
    }

    suspend fun acceptTransaction(id: Long) = withContext(Dispatchers.IO) {
        proposalApi.acceptTransaction(id, AcceptTransaction(true))
    }

    suspend fun rejectTransaction(id: Long) = withContext(Dispatchers.IO) {
        proposalApi.acceptTransaction(id, AcceptTransaction(false))
    }

    suspend fun updateTransactionStatus(id: Long, status: RequestStatus) = withContext(Dispatchers.IO) {
        val file = fileRepository.uploadFile()

        proposalApi.updateTransactionStatus(id, UpdateTransactionStatus(status.nameR))

        if (file != null) {
            proposalApi.updateTransaction(id, UpdateTransaction(id, file))
        }
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        proposalApi.deleteProposal(id)
    }

    suspend fun editProposal(
        title: String, description: String, status: String,
        tags: List<Pair<TagTitle, List<String>>>
    ) = withContext(Dispatchers.IO) {
        proposalApi.editProposal(NavigationParams.proposalDetailsItemId, EditHelpData(description, status, title))

        proposalApi.updateTags(
            UpdateHelpTags(
                eventId = NavigationParams.proposalDetailsItemId.toInt(),
                tags.map { CreateTag(it.first, if (it.first == TagTitle.Location) it.second else it.second.filter { it.isBlank().not() }) },
                eventType = "proposal-event",
            )
        )
    }
}