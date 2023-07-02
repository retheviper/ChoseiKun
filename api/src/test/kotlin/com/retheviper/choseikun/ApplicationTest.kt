package com.retheviper.choseikun

import com.retheviper.choseikun.plugins.configureRouting
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

class ApplicationTest : FreeSpec({
    "testRoot" {
        testApplication {
            application {
                configureRouting()
            }
            client.get("/v1").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText() shouldBe "Hello World!"
            }
        }
    }
})