package com.ixxus.hastalavista.controller

import com.ixxus.hastalavista.ConfigObject
import com.ixxus.hastalavista.store.Page
import org.springframework.web.bind.annotation._

import scala.xml.XML

/**
  * Controller providing search API access to the Store(s)
  *
  * Created by Michael.Seddon on 10-May-17.
  */
@RestController
class SearchController() extends AbstractController {

    //TODO: extract controller logic in to service!

    @RequestMapping(value = Array("/find/page"),
        method = Array(RequestMethod.GET))
    def getPages(@RequestParam url: String) = {
        val found = pageStore.pages.filter(p => p.url == url)
        (for (f <- found) yield f.puid + " " + f.url).mkString("\n")
    }
}
