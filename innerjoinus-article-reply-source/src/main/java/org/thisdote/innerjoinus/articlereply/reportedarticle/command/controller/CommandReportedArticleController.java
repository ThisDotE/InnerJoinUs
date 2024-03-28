package org.thisdote.innerjoinus.articlereply.reportedarticle.command.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thisdote.innerjoinus.articlereply.article.command.service.CommandArticleService;

@RestController
@RequestMapping("/")
public class CommandReportedArticleController {
    private final ModelMapper modelMapper;
    private final CommandArticleService commandArticleService;

    public CommandReportedArticleController(ModelMapper modelMapper, CommandArticleService commandArticleService) {
        this.modelMapper = modelMapper;
        this.commandArticleService = commandArticleService;
    }
}
