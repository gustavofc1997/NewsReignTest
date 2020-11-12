package com.gforeroc.reign.data.mappers

import com.gforeroc.reign.data.base.MockableTest
import com.gforeroc.reign.data.models.APINews
import org.junit.Assert
import org.junit.Test
import java.time.format.DateTimeParseException

class APINewsMapperTest : APINewsDependantTest() {
    @Test
    fun shouldBeAbleToMapFromAPINews() {
        val new = APINewsMapper.map(apiNew)
        Assert.assertNotNull(new)
        Assert.assertEquals("Hello from the other side", new.title)
    }

    @Test
    fun shouldGetAuthorNotFound() {
        val new = APINewsMapper.map(apiNewNullAuthor)
        Assert.assertNotNull(new)
        Assert.assertEquals("Author not found", new.author)
    }

    @Test
    fun shouldGetTitleNotFound() {
        val new = APINewsMapper.map(apiNewNullAuthor)
        Assert.assertNotNull(new)
        Assert.assertEquals("Title not found", new.title)
    }


    @Test(expected = DateTimeParseException::class)
    fun shouldGetDateNotFound() {
        val new = APINewsMapper.map(apiNewWrongDate)
        Assert.assertNotNull(new)
        Assert.assertEquals("Hello from the other side", new.title)
        Assert.assertEquals("Date not found", new.creationDate)
    }
}


abstract class APINewsDependantTest : MockableTest {

    val apiNew = APINews(
        "2020-11-09T14:02:26.000Z",
        "Hello from the other side",
        null,
        "Gustavo Forero",
        "https://nymag.com/intelligencer/2020/11/inside-the-new-york-times-heated-reckoning-with-itself.html"
    )

    val apiNewWrongDate = APINews(
        "2020-1-1",
        "Hello from the other side",
        null,
        "Gustavo Forero",
        "https://nymag.com/intelligencer/2020/11/inside-the-new-york-times-heated-reckoning-with-itself.html"
    )

    val apiNewNullAuthor = APINews(
        "2020-11-09T14:02:26.000Z",
        null,
        null,
        null,
        "https://nymag.com/intelligencer/2020/11/inside-the-new-york-times-heated-reckoning-with-itself.html"
    )
}
