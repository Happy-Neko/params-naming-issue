package com.github.issue

import com.fasterxml.jackson.databind.node.ObjectNode
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
internal class PropertyNamingStrategyTest(
    private val sampleClient: SampleClient
) {
    @Test
    fun `HTTP client should use property naming strategy`() {
        val name = "John"
        assertEquals(name, sampleClient.extractPropertyValue(firstName = name))
    }

    @Client("/")
    internal interface SampleClient {
        @Post(uri ="/test", consumes = [TEXT_PLAIN])
        fun extractPropertyValue(firstName: String): String
    }

    @Controller("/")
    internal class SampleController {
        @Post(uri = "/test", produces = [TEXT_PLAIN])
        fun extractPropertyValue(@Body request: ObjectNode): String {
            return request.path("first_name").textValue()
        }
    }
}
