package com.rjp.navigationview;

/**
 * @author Gimpo create on 2018/1/23 14:56
 * email : jimbo922@163.com
 */

public class TabModel {
    private String title;
    private int iconResource;

    public TabModel(String title, int iconResource){
        this.title = title;
        this.iconResource = iconResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
