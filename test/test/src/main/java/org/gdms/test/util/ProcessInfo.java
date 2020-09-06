package org.gdms.test.util;

import org.gdms.test.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class ProcessInfo {
    private String name;
    private int finished;
    private int unfinished;
    private List<Student> finishedStu;
    private List<Student> unfinishedStu;
    private String end_time;
}
