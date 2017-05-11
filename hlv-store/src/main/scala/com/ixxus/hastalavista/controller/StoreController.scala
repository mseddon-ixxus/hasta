package com.ixxus.hastalavista.controller

import com.ixxus.hastalavista.ConfigObject
import com.ixxus.hastalavista.store.Page
import org.springframework.web.bind.annotation._

import scala.xml.XML

/**
  * Controller giving API access to the Store(s)
  *
  * Created by Michael.Seddon on 10-May-17.
  */
@RestController
class StoreController() extends AbstractController {

    //TODO: extract controller logic in to service!

    @RequestMapping(value = Array("/page"),
        method = Array(RequestMethod.POST),
        consumes = Array("text/plain"))
    def addPage(@RequestBody body: String) = {
        val pbod = XML.loadString(body)
        val url = (pbod \ "url").head.text
        val cont = (pbod \ "contents").head.text
        pageStore += Page(url, cont)
        pageStore.pages.size + " " + url + " " + cont
    }

    @RequestMapping(value = Array("/pages"),
        method = Array(RequestMethod.POST),
        consumes = Array("text/plain"))
    def addPages(@RequestBody body: String) = {
        val pbod = XML.loadString(body)
        val urls = (pbod \ "page" \ "url").map(u => u.text)
        val conts = (pbod \ "page" \ "contents").map(c => c.text)

        urls.zip(conts).foreach(t => pageStore += Page(t._1, t._2))
    }

    @RequestMapping(value = Array("/pages"),
        method = Array(RequestMethod.GET))
    def getPages() = {
        (for (p <- pageStore.pages) yield p.puid + " " + p.url).mkString("\n")
    }
}
