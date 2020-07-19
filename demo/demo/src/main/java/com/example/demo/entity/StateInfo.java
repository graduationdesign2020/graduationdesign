package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class StateInfo {
    private List<State> states;
    private State currentState;
}
