package com.ixxus.hastalavista.store

import com.ixxus.hastalavista.StoreTest

/**
  * Created by Michael.Seddon on 09-May-17.
  */
class PageStoreTest extends StoreTest with PageStoreComponent {

    override val pageStore: PageStore = new PageStoreImpl
    val pageToAdd = new Page(null, null, null, null, null)
    var pageToAddRemove = new Page(null, null, null, null, null)

    override def beforeEach(): Unit = {
        pageStore.pages.clear()
    }

    "PageStore" should "exist" in {
        pageStore should not be nullable
    }

    it should "have a HashSet of Pages" in {
        pageStore.pages should not be nullable
        pageStore.pages.size should be(0)
    }

    it should "allow a Page to be added the HashSet" in {
        pageStore.pages += pageToAdd

        pageStore.pages.size should be(1)
        pageStore.pages.head should be(pageToAdd)
    }

    it should "allow a Page to be removed from the HashSet" in {
        pageStore.pages += pageToAdd
        pageStore.pages += pageToAddRemove
        pageStore.pages -= pageToAddRemove

        pageStore.pages.size should be(1)
        pageStore.pages.head should be(pageToAdd)
    }

    it should "allow a Page to be retrieved from the HashSet" in {
        pageStore.pages += pageToAdd

        pageStore.pages(pageToAdd) should be(true)

        val retrievedPage = pageStore.pages.find(p => p == pageToAdd)
        retrievedPage.get should be(pageToAdd)

    }

}
