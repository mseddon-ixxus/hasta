package com.ixxus.hastalavista.term

import com.ixxus.hastalavista.store.Page

/**
  * Created by Michael.Seddon on 15-May-17.
  */
case class SearchTerm(term: String) {}
/*

case class Relevancy() extends SearchTerm(_) {
    val search = (p: Set[Page], t: String) => p.filter(ir => ir.parsedContents.contains(t))
}
*/



object SearchTerm {
}
