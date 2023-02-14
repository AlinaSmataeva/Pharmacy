package org.smataeva.finalproject.command;

import org.smataeva.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
