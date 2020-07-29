package com.example.demo.utils;

import com.example.demo.entity.State;
import lombok.Data;

@Data
public class StateInfo {
    private State sta;
    private String state;
    private int stateNum;
    private int submit;
    private String end_time;
    
    public void init(int state) {
        this.submit = 0;
        this.stateNum = state;
    }

    public void transfer() {
        int s = -1;
        if (this.sta != null) {
            this.submit = sta.getSubmit();
            s = sta.getState();
        }
        else s = this.stateNum;

        switch (s) {
            case 0: this.state = "任务书";break;
            case 1: this.state = "开题报告";break;
            case 2: this.state = "中期检查";break;
            case 3: this.state = "论文定稿";break;
            case 4: this.state = "论文最终稿";
        }
    }
}
