package com.mtechnologies.martin.bulsuapp.pages;

import org.jsoup.select.Elements;

import java.util.List;

import pl.droidsonroids.jspoon.annotation.Selector;

/**
 * Created by martin on 4/8/18.
 */

public class Term {

    @Selector("#xterms >span >a.list-group-item")public String[] termDetails;
    @Selector(value = "#xterms >span >a",attr = "id")public String[] termID;

}
