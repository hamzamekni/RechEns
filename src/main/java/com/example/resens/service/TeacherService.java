package com.example.resens.service;

import com.example.resens.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    Teacher updateTeacher(Long id, Teacher updatedTeacher);
    void deleteTeacher(Long id);
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
}
