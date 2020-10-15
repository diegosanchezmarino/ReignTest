package com.dsm.reigntestapp.network.deserializer

import com.dsm.reigntestapp.DateFormatter
import com.dsm.reigntestapp.model.Article
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ArticlesDeserializer : JsonDeserializer<ArticlesResponse> {


    val dateFormatter: DateFormatter = DateFormatter()


    override fun deserialize(
        jsonElement: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ArticlesResponse {

        val hitsArray: JsonArray = jsonElement.asJsonObject["hits"].asJsonArray

        var newsList = ArrayList<Article>()

        hitsArray.forEach {
            var author = it.asJsonObject["author"].asString

            var title =
                if (it.asJsonObject.get("title").isJsonNull) null else it.asJsonObject["title"].asString
            var storyTitle = it.asJsonObject["story_title"].asString
            var createdAt = dateFormatter.formatDate(it.asJsonObject["created_at"].asString)
            var createdAtI = it.asJsonObject["created_at_i"].asLong
            var storyId = it.asJsonObject["story_id"].asString


            val new = Article(
                title = title,
                storyTitle = storyTitle,
                creationTime = createdAt,
                author = author,
                id = storyId,
                createdAtI = createdAtI
            )
            newsList.add(new)
        }

        newsList.sortedByDescending { it.createdAtI }
        return ArticlesResponse(newsList)
    }


}