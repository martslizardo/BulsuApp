package com.mtechnologies.martin.bulsuapp.pages;

import java.util.List;

import pl.droidsonroids.jspoon.annotation.Selector;

/**
 * Created by martin on 3/31/18.
 */

public class Dashboard {

    @Selector("#s1 >.princeh1") public String profileName;
    @Selector("#s1 >p")public List<String> profileDetails;
}
