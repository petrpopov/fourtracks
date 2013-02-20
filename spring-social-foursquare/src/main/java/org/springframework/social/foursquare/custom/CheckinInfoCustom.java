package org.springframework.social.foursquare.custom;

import java.util.List;

/**
 * User: petrpopov
 * Date: 20.02.13
 * Time: 16:43
 */
public class CheckinInfoCustom {

    private int count;
    private List<CheckinCustom> items;

    public CheckinInfoCustom() {
    }

    public CheckinInfoCustom(int count, List<CheckinCustom> items) {
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CheckinCustom> getItems() {
        return items;
    }

    public void setItems(List<CheckinCustom> items) {
        this.items = items;
    }
}
