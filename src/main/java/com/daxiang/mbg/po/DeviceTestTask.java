package com.daxiang.mbg.po;

import com.daxiang.mbg.po.GlobalVar;
import com.daxiang.model.vo.Testcase;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DeviceTestTask implements Serializable {

    /** 出错，无法运行 */
    public static final int ERROR_STATUS = -1;
    /** 未开始 */
    public static final int UNSTART_STATUS = 0;
    /** 运行中 */
    public static final int RUNNING_STATUS = 1;
    /** 完成 */
    public static final int FINISHED_STATUS = 2;

    private Integer id;

    /**
     * 项目id
     *
     * @mbg.generated
     */
    private Integer projectId;

    /**
     * 测试任务id
     *
     * @mbg.generated
     */
    private Integer testTaskId;

    /**
     * 设备id
     *
     * @mbg.generated
     */
    private String deviceId;

    /**
     * 状态：-1:无法运行 0:未开始 1:运行中 2:已完成
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 开始测试时间
     *
     * @mbg.generated
     */
    private Date startTime;

    /**
     * 结束测试时间
     *
     * @mbg.generated
     */
    private Date endTime;

    /**
     * 全局变量
     *
     * @mbg.generated
     */
    private java.util.List<GlobalVar> globalVars;

    /**
     * BeforeClass
     *
     * @mbg.generated
     */
    private Action beforeClass;

    /**
     * BeforeMethod
     *
     * @mbg.generated
     */
    private Action beforeMethod;

    /**
     * AfterClass
     *
     * @mbg.generated
     */
    private Action afterClass;

    /**
     * AfterMethod
     *
     * @mbg.generated
     */
    private Action afterMethod;

    /**
     * 执行的测试用例
     *
     * @mbg.generated
     */
    private java.util.List<com.daxiang.model.vo.Testcase> testcases;

    /**
     * agent转换后的代码
     *
     * @mbg.generated
     */
    private String code;

    /**
     * status: -1, 错误信息
     *
     * @mbg.generated
     */
    private String errMsg;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTestTaskId() {
        return testTaskId;
    }

    public void setTestTaskId(Integer testTaskId) {
        this.testTaskId = testTaskId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public java.util.List<GlobalVar> getGlobalVars() {
        return globalVars;
    }

    public void setGlobalVars(java.util.List<GlobalVar> globalVars) {
        this.globalVars = globalVars;
    }

    public Action getBeforeClass() {
        return beforeClass;
    }

    public void setBeforeClass(Action beforeClass) {
        this.beforeClass = beforeClass;
    }

    public Action getBeforeMethod() {
        return beforeMethod;
    }

    public void setBeforeMethod(Action beforeMethod) {
        this.beforeMethod = beforeMethod;
    }

    public Action getAfterClass() {
        return afterClass;
    }

    public void setAfterClass(Action afterClass) {
        this.afterClass = afterClass;
    }

    public Action getAfterMethod() {
        return afterMethod;
    }

    public void setAfterMethod(Action afterMethod) {
        this.afterMethod = afterMethod;
    }

    public java.util.List<com.daxiang.model.vo.Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(java.util.List<com.daxiang.model.vo.Testcase> testcases) {
        this.testcases = testcases;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", testTaskId=").append(testTaskId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", globalVars=").append(globalVars);
        sb.append(", beforeClass=").append(beforeClass);
        sb.append(", beforeMethod=").append(beforeMethod);
        sb.append(", afterClass=").append(afterClass);
        sb.append(", afterMethod=").append(afterMethod);
        sb.append(", testcases=").append(testcases);
        sb.append(", code=").append(code);
        sb.append(", errMsg=").append(errMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}