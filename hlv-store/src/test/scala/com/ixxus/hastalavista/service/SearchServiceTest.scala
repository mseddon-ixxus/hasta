package com.ixxus.hastalavista.service

import com.ixxus.hastalavista.store.Page
import com.ixxus.hastalavista.{ConfigObject, StoreTest}

/**
  * Created by Michael.Seddon on 09-May-17.
  */
class SearchServiceTest extends StoreTest {

    val searchService = ConfigObject.searchService
    val pageStore = ConfigObject.storeService.pageStore

    override def beforeEach(): Unit = {
        pageStore.pages = Set()
        pageStore.pages += Page("page1", "exact match")
        pageStore.pages += Page("page2", "exact word match")
        pageStore.pages += Page("page3", "exact word word match")
        pageStore.pages += Page("page4", "exact word word word match")
    }

    "SearchService" should "have access to the PageStore" in {
        searchService should not be nullable
        searchService.pageStore should not be nullable
    }

    it should "provide a mechanism to search for pages by URL" in {
        pageStore.pages += Page("pageurl", "pagecontents")
        val foundPages = searchService.findPage("pageurl")

        foundPages should not be nullable
        foundPages should not be empty
        foundPages.size should be(1)
        foundPages.head.url should be("pageurl")
    }

    it should "provide a mechanism to search for pages by a single word search term, ordering by occurance" in {
        val foundPages = searchService.find("word")
        foundPages should not be nullable
        foundPages should not be empty
        foundPages.size should be(3)

        foundPages(0)._1.url should be("page4")
        foundPages(1)._1.url should be("page3")
        foundPages(2)._1.url should be("page2")

    }

    it should "provide a mechanism to search for pages by a multi word search term, ordering by relevancy" in {

        val foundPages = searchService.find("exact match")

        foundPages should not be nullable
        foundPages should not be empty
        foundPages.size should be(4)

        foundPages(0)._1.url should be("page1")
        foundPages(1)._1.url should be("page2")
        foundPages(2)._1.url should be("page3")
        foundPages(3)._1.url should be("page4")

    }

}
