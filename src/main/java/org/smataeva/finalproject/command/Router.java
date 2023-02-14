package org.smataeva.finalproject.command;

public class Router {
    private final RouterType RouterType;
    private final String PagePath;

    public Router(RouterType RouterType , String PagePass) {
        this.RouterType = RouterType;
        this.PagePath = PagePass;
    }

    public RouterType getRouterType() {
        return RouterType;
    }

    public String getPagePath() {
        return PagePath;
    }

    public enum RouterType {
        FORWARD, REDIRECT
    }

}

