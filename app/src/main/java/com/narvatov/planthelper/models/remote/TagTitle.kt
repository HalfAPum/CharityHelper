package com.narvatov.planthelper.models.remote

enum class TagTitle(val title: String) {
    Location("location"),
    AgeGroup("age-group"),
    Topic("topic"),
}

fun CreateTag(
    tagTitle: TagTitle,
    values: List<String>,
    eventType: String = "proposal-event"
) = Tag(
    title = tagTitle.title,
    values = values,
//    eventType = eventType
)
