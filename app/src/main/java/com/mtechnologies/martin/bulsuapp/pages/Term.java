package com.mtechnologies.martin.bulsuapp.pages;

import java.util.List;

import pl.droidsonroids.jspoon.annotation.Selector;

/**
 * Created by martin on 4/8/18.
 */

public class Term {
    @Selector("#xterms >span >a")public List<String> termDetails;

}
