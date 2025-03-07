package com.example.mysentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class LocalDemo {
    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
//            try (Entry entry = SphU.entry("HelloWorld")) {
//                // 被保护的逻辑
//                System.out.println("hello world");
//
//            } catch (BlockException ex) {
//                // 处理被流控的逻辑
//                System.out.println("blocked!");
//            }
            new LocalDemo().helloWorld();
        }
    }

    @SentinelResource("HelloWorld002")
    public void helloWorld() {
        // 资源中的逻辑
        System.out.println("hello world002");
    }
    public static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();


//        rules.add(setRule("HelloWorld",RuleConstant.FLOW_GRADE_QPS,10)) ;
        rules.add(setRule("HelloWorld002",RuleConstant.FLOW_GRADE_QPS,9)) ;

        FlowRuleManager.loadRules(rules);
    }

    public static FlowRule  setRule(String rsName, int type, int t){

        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(t);
        return rule;
    }
}
