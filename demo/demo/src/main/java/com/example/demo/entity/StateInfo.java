package com.example.demo.entity;

import lombok.Data;

@Data
public class StateInfo {
    private State sta;
    private String state;
    private int submit;
    private String start_time;
    private String end_time;

    public void init(int state) {
        State state1 = new State();
        state1.setState(state);
        this.submit = 4;
    }

    public void transfer() {
        this.submit = sta.getSubmit();
        this.start_time = sta.getStart_time();
        this.end_time = sta.getEnd_time();
        switch (sta.getState()) {
            case 0: this.state = "任务书";
            case 1: this.state = "开题报告";
            case 2: this.state = "中期检查";
            case 3: this.state = "论文定稿";
            case 4: this.state = "论文最终稿";
        }
    }
}
