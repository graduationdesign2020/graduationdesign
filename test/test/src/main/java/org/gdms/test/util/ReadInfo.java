package org.gdms.test.util;

import org.gdms.test.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class ReadInfo {
    private List<Student> studentsRead;
    private List<Student> studentsUnread;
    private int Read;
    private int unRead;
}
