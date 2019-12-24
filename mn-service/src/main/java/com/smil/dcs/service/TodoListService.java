package com.smil.dcs.service;

import com.smil.dcs.dto.TodoDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TodoListService {
    List<TodoDto> getTodoList();

    static List<TodoDto> runParallel(TodoListService... args) {
        return Stream.of(args)
                .parallel()
                .map(TodoListService::getTodoList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
