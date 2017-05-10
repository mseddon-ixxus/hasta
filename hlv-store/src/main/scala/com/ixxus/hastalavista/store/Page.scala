package com.ixxus.hastalavista.store

import java.util.UUID

/**
  * Representation of a Page
  *
  * Created by Michael.Seddon on 09-May-17.
  */
class Page(val rawContents: String,
           val delimContents: Array[String],
           val parsedContents: Map[String, Int],
           val url: String,
           val encodedUrl: String) {

    private[this] var relatedPages: Set[Page] = Set()
    private[this] var meta: Map[String, String] = Map()

    val puid: String = UUID.randomUUID.toString

    def +=(pageToAdd: Page) { relatedPages += pageToAdd }

    def +=(t: (String, String)) { meta += t }

    def related = relatedPages
    def metadata = meta
}
