package org.graduationdesign.gdmsserversearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private double id;
    private double type;
    private String title;
    private String content;
}

