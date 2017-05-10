package com.ixxus.hastalavista.store

import com.ixxus.hastalavista.StoreTest
import sun.misc.BASE64Encoder

/**
  * Created by Michael.Seddon on 09-May-17.
  */
class PageTest extends StoreTest {

    val pageToAdd = new Page(null, null, null, null, null)
    var pageToAddRemove = new Page(null, null, null, null, null)

    override def beforeEach(): Unit = {

    }

    "A Page" should "be assigned a unique PUID on creation" in {
        val puidPage = new Page(null, null, null, null, null)
        val otherPuidPage = new Page(null, null, null, null, null)

        puidPage.puid should not be nullable
        puidPage.puid should not be empty

        puidPage.puid should not be otherPuidPage.puid
    }

    it should "be able to have related pages linked to it" in {
        val childPage = new Page(null, null, null, null, null)
        val parentPage = new Page(null, null, null, null, null)

        parentPage += childPage

        parentPage.related.contains(childPage) should be(true)
        parentPage.related.find(p => p == childPage).get should be(childPage)
    }

    it should "be able to have a tree of pages linked to it" in {
        val grandchildPage = new Page(null, null, null, null, null)
        val childPage = new Page(null, null, null, null, null)
        val parentPage = new Page(null, null, null, null, null)

        childPage += grandchildPage
        parentPage += childPage

        parentPage.related.contains(childPage) should be(true)
        val retrievedChildPage = parentPage.related.find(p => p == childPage).get
        retrievedChildPage should be(childPage)

        childPage.related.contains(grandchildPage) should be(true)
        val retrievedGrandchildPage = childPage.related.find(p => p == grandchildPage).get
        retrievedGrandchildPage should be(grandchildPage)

        val retrievedGcPage2 = retrievedChildPage.related.find(p => p == retrievedGrandchildPage).get
        retrievedGrandchildPage should be(retrievedGcPage2)
    }

    it should "be able to store generic metadata" in {
        val metaPage = new Page(null, null, null, null, null)
        metaPage += ("metaKey1", "metaVal1")
        metaPage += ("metaKey2", "metaVal2")

        metaPage.metadata.size should be(2)
        metaPage.metadata("metaKey1") should be("metaVal1")
        metaPage.metadata("metaKey2") should be("metaVal2")
    }

    it should "be able to store contents in raw HTML format" in {
        val rawContents = "<b>this is valid HTML</b>"

        val rawPage = new Page(rawContents, null, null, null, null)

        rawPage.rawContents should not be nullable
        rawPage.rawContents should not be empty
        rawPage.rawContents should be(rawContents)

    }

    it should "be able to store an array of space delimited strings representing content" in {
        val rawContents = "<b>this is valid HTML</b>"
        val delimContents = rawContents.split(' ')

        val splitPage = new Page(null, delimContents, null, null, null)
        splitPage.delimContents.length should be(4)
        splitPage.delimContents(2) should be("valid")
    }

    it should "be able to store a map of words and occurrences" in {
        val occMap: Map[String, Int] = Map("smile" -> 1, "coat" -> 2, "sun" -> 4)
        val occPage = new Page(null, null, occMap, null, null)

        occPage.parsedContents should not be nullable
        occPage.parsedContents should not be empty
        occPage.parsedContents("smile") should be(1)
        occPage.parsedContents("coat") should be(2)
        occPage.parsedContents("sun") should be(4)
    }

    it should "be able to store a URL" in {
        val url = "http://www.scala-lang.org"
        val urlPage = new Page(null, null, null, url, null)

        urlPage.url should not be nullable
        urlPage.url should not be empty
        urlPage.url should be(url)
    }

    it should "be able to store an encoded URL" in {
        val url = "http://www.scala-lang.org"
        val encUrl = new BASE64Encoder().encode(url.getBytes())
        val encUrlPage = new Page(null, null, null, null, encUrl)

        encUrlPage.encodedUrl should not be nullable
        encUrlPage.encodedUrl should not be empty
        encUrlPage.encodedUrl should be(encUrl)
    }
}
