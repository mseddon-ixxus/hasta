package com.ixxus.hastalavista.store

import scala.collection.mutable

/**
  * Page related Store
  *
  * Created by Michael.Seddon on 09-May-17.
  */
trait PageStoreComponent {

    trait PageStore {
        val pages : mutable.HashSet[Page]
    }

    val pageStore: PageStore

    class PageStoreImpl extends PageStore {
        override val pages: mutable.HashSet[Page] = mutable.HashSet()
    }
}
