package com.ixxus.hastalavista.service

import com.ixxus.hastalavista.store.Page

/**
  * Created by Michael.Seddon on 12-May-17.
  */
class SearchService extends AbstractService {

    def findPage(url: String): Set[Page] = pageStore.pages.filter(p => p.url == url)

    def find(term: String): List[(Page, Int)] = {
        var distances: List[(Page, Int)] = List()

        val searchTerms = term.split(' ')
        if (searchTerms.size > 1) {
            pageStore.pages.foreach(p => {
                val what = searchTerms.map(kw => (kw, p.delimContents.indexOf(kw))).filter(_._2 >= 0).toList
                if (what.nonEmpty) {
                    val dist = distCalc(searchTerms.toSeq.toList, what, 0, 0, what.head._2, 0)
                    if (dist > 0) distances = (p, dist) :: distances
                }
            })

            distances.sortWith(_._2 < _._2)
        } else {
            pageStore.pages.foreach(p => {
                val what = p.delimContents.count(_ == searchTerms(0))
                if (what > 0) distances = (p, what) :: distances
            })

            distances.sortWith(_._2 > _._2)
        }
    }

    def distCalc(terms: List[String], found: List[(String, Int)], termPointer: Int, foundPointer: Int, pointer: Int, distance: Int): Int = {
        if (foundPointer >= found.size || termPointer >= terms.size) {
            distance
        } else {
            if (terms(termPointer) == found(foundPointer)._1) {
                //we match, so look for next term
                val dist = distance + (found(foundPointer)._2 - pointer)
                distCalc(terms, found, termPointer + 1, foundPointer + 1, found(foundPointer)._2, dist)
            } else {
                //we don't match, so look for first term
                distCalc(terms, found, 0, foundPointer + 1, found(foundPointer)._2, 0)
            }
        }
    }

}
