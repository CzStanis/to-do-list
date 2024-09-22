//package com.Wproject.to_do_list.service;
//
//import com.Wproject.to_do_list.entity.TaskCategory;
//import com.Wproject.to_do_list.repository.TaskCategoryRepository;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.fasterxml.jackson.databind.util.Converter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToTaskCategoryConverter implements Converter<String, TaskCategory> {
//
//    @Autowired
//    private TaskCategoryRepository taskCategoryRepository;
//
//    @Override
//    public TaskCategory convert(String source) {
//        return taskCategoryRepository.findByName(source);
//    }
//
//    @Override
//    public JavaType getInputType(TypeFactory typeFactory) {
//        return typeFactory.constructType(String.class);
//    }
//
//    @Override
//    public JavaType getOutputType(TypeFactory typeFactory) {
//        return typeFactory.constructType(TaskCategory.class);
//    }
//}
//
