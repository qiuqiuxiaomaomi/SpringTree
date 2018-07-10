package com.bonaparte.entity;

import javax.persistence.*;

@Table(name = "p_quartz_task")
public class QuartzTask {
    @Id
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "trigger_name")
    private String triggerName;

    private String time;

    private String remark;

    /**
     * 相关主键id
     */
    @Column(name = "rel_id")
    private Long relId;

    /**
     * @return task_id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return trigger_name
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * @param triggerName
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取相关主键id
     *
     * @return rel_id - 相关主键id
     */
    public Long getRelId() {
        return relId;
    }

    /**
     * 设置相关主键id
     *
     * @param relId 相关主键id
     */
    public void setRelId(Long relId) {
        this.relId = relId;
    }
}