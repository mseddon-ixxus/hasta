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

    val searchService = ConfigObject.searchService

    @RequestMapping(value = Array("/find/page"),
        method = Array(RequestMethod.GET))
    def findPages(@RequestParam url: String) = {
        (for (f <- searchService.findPage(url)) yield f.puid + " " + f.url + "---" + f.rawContents).mkString("\n")

    }

    @RequestMapping(value = Array("/find/term"),
        method = Array(RequestMethod.GET))
    def find(@RequestParam term: String) = {
        (for (f <- searchService.find(term))
            yield f._1.puid + " " + f._1.url + " --- " + f._2).mkString("\n")
    }
}
