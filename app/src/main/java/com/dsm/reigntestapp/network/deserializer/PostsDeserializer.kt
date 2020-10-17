package com.dsm.reigntestapp.network.deserializer

import com.dsm.reigntestapp.DateFormatter
import com.dsm.reigntestapp.model.Post
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PostsDeserializer : JsonDeserializer<PostsResponse> {


    private val dateFormatter: DateFormatter = DateFormatter()


    override fun deserialize(
        jsonElement: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PostsResponse {

        val hitsArray: JsonArray = jsonElement.asJsonObject["hits"].asJsonArray

        var newsList = ArrayList<Post>()

        hitsArray.forEach {
            var author = it.asJsonObject["author"].asString

            var title = if (it.asJsonObject.get("title").isJsonNull) null else it.asJsonObject["title"].asString
            var storyTitle = if (it.asJsonObject.get("story_title").isJsonNull) null else it.asJsonObject["story_title"].asString
            var createdAt = it.asJsonObject["created_at"].asString
            var createdAtI = it.asJsonObject["created_at_i"].asLong
            var id = it.asJsonObject["objectID"].asString

            var storyUrl: String? =  null
            if (!it.asJsonObject.get("story_url").isJsonNull)
                storyUrl = it.asJsonObject["story_url"].asString
            else if(!it.asJsonObject.get("url").isJsonNull)
                storyUrl = it.asJsonObject["url"].asString


            val new = Post(
                title = title,
                storyTitle = storyTitle,
                creationTime = createdAt,
                author = author,
                id = id,
                createdAtI = createdAtI,
                storyUrl = storyUrl
            )
            newsList.add(new)
        }

        return PostsResponse(newsList)
    }


}